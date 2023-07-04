package cg.viciousconcepts.taxesautomobiles.repositories

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
            emit(123.45f)
        }
    }

    fun getRegistrationTax(tax: Tax): Flow<Float> {
        return flow {
            registrationRepository
                .getRegistrationDate()
                .zip(engineSizeRepository.getEngineCv(tax.engineSize)) { registrationTable, cv ->
                    max(
                        registrationTable
                            .last { it.first <= tax.age }
                            .second
                            .last { it.first.first == tax.enginePower }
                            .second,
                        registrationTable
                            .last { it.first <= tax.age }
                            .second
                            .first { cv <= it.first.second }
                            .second
                    )
                }.collect {
                    emit(it)
                }
        }
    }
}