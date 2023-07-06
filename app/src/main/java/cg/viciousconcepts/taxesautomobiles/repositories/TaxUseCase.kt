package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Tax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import java.lang.Float.max

class TaxUseCase(
    private val engineSizeRepository: EngineSizeRepository,
    private val registrationRepository: RegistrationRepository
) : BaseUseCase {

    fun getAnnualTax(tax: Tax): Flow<Float> {
        return flow {
            when (tax.engineType) {
                EngineType.Petrol, EngineType.Diesel, EngineType.Hybrid, EngineType.LPG -> {
                    engineSizeRepository
                        .getEngineCv(tax.engineSize)
                        .zip(registrationRepository.getAnnualData()) { cv, annualData ->
                            Pair(cv, annualData)
                        }
                        .zip(registrationRepository.getLpgAnnualData()) { (cv, annual), lpgAnnual ->
                            val byEngineSize = annual
                                .first { tax.engineSize in it.first.first }
                                .second

                            val byLpg = if (tax.engineType == EngineType.LPG)
                                lpgAnnual
                                    .first { cv <= it.first }
                                    .second
                            else
                                0f

                            byEngineSize + byLpg
                        }.collect {
                            emit(it)
                        }
                }

                EngineType.Electric -> emit(9999.99f)
            }

        }
    }

    fun getRegistrationTax(tax: Tax): Flow<Float> {
        return flow {
            when (tax.engineType) {
                EngineType.Petrol, EngineType.Diesel, EngineType.Hybrid, EngineType.LPG -> {
                    // TODO: FIXME:
                    registrationRepository
                        .getRegistrationData()
                        .zip(registrationRepository.getLpgAnnualData()) { registration, registrationLpg ->
                            Pair(registration, registrationLpg)
                        }
                        .zip(engineSizeRepository.getEngineCv(tax.engineSize)) { registrationTable, cv ->
                            max(
                                registrationTable
                                    .first
                                    .last { it.first <= tax.age }
                                    .second
                                    .last { it.first.first == tax.enginePower }
                                    .second,
                                registrationTable
                                    .first
                                    .last { it.first <= tax.age }
                                    .second
                                    .first { cv <= it.first.second }
                                    .second
                            ) + if (tax.engineType == EngineType.LPG)
                                registrationTable.second.first { cv <= it.first }.second
                            else
                                0f
                        }.collect {
                            emit(it)
                        }
                }

                else -> emit(0f)
            }

        }
    }
}