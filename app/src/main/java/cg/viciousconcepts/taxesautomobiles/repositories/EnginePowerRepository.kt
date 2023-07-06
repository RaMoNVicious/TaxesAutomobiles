package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import kotlinx.coroutines.flow.flow

class EnginePowerRepository {

    fun getData() =
        flow {
            emit(
                listOf(
                    EnginePower.UpTo70,
                    EnginePower.UpTo85,
                    EnginePower.UpTo100,
                    EnginePower.UpTo110,
                    EnginePower.UpTo120,
                    EnginePower.UpTo155,
                    EnginePower.MoreThan155,
                )
            )
        }

}