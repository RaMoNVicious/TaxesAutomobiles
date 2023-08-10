package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Tax
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip

class TaxUseCase(
    private val engineSizeRepository: EngineSizeRepository,
    private val registrationRepository: RegistrationRepository,
    private val emissionRepository: EmissionRepository
) : BaseUseCase {

    fun getAnnualTax(tax: Tax): Flow<Float> {
        return flow {
            when (tax.engineType) {
                EngineType.Petrol, EngineType.Diesel, EngineType.Hybrid, EngineType.LPG -> {
                    engineSizeRepository
                        .getCV(tax.engineSize)
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

                EngineType.Electric -> {
                    registrationRepository
                        .getAnnualData()
                        .collect { items ->
                            items
                                .first()
                                .second
                                .let {
                                    emit(it)
                                }
                        }
                }
            }

        }
    }

    fun getRegistrationTax(tax: Tax): Flow<Float> {
        return flow {
            when (tax.engineType) {
                EngineType.Petrol, EngineType.Diesel, EngineType.Hybrid, EngineType.LPG -> {
                    registrationRepository
                        .getRegistrationData()
                        .zip(emissionRepository.getData()) { registration, emission ->
                            Pair(registration, emission)
                        }
                        .zip(engineSizeRepository.getCV(tax.engineSize)) { (registration, emissions), cv ->
                            val emission = emissions
                                .let {
                                    it[kotlin.math.max(
                                        0,
                                        it.indexOf(tax.emission) - when (tax.children) {
                                            3 -> 1
                                            4 -> 2
                                            else -> 0
                                        }
                                    )]
                                }

                            kotlin.math.max(
                                registration
                                    .last { it.first <= tax.age }
                                    .second
                                    .first { it.first.first == tax.enginePower }
                                    .second,
                                registration
                                    .last { it.first <= tax.age }
                                    .second
                                    .first { cv <= it.first.second }
                                    .second
                            ) + emissions
                                .first { it == emission }
                                .value
                        }.collect {
                            emit(it)
                        }
                }

                EngineType.Electric -> {
                    registrationRepository
                        .getRegistrationData()
                        .collect { registration ->
                            emit(
                                registration
                                    .first()
                                    .second
                                    .first()
                                    .second
                            )
                        }
                }
            }

        }
    }
}