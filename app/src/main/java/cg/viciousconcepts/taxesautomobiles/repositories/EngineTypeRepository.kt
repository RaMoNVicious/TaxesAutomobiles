package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import kotlinx.coroutines.flow.flow

class EngineTypeRepository {

    fun getEngineSizes() =
        flow {
            emit(
                listOf(
                    EngineType.Petrol,
                    EngineType.Diesel,
                    EngineType.LPG,
                    EngineType.Hybrid,
                    EngineType.Electric
                )
            )
        }
}