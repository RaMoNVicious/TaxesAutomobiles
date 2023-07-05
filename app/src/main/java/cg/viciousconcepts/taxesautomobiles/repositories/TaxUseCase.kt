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
        // TODO: calculate
        return flow {
            emit(0f)
        }
    }

    fun getRegistrationTax(tax: Tax): Flow<Float> {
        return flow {
            when (tax.engineType) {
                EngineType.Petrol, EngineType.Diesel, EngineType.Hybrid, EngineType.LPG -> {
                    registrationRepository
                        .getRegistrationData()
                        .zip(registrationRepository.getLpgRegistrationData()) { registration, registrationLpg ->
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