package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.Emissions
import kotlinx.coroutines.flow.flow

class EmissionRepository {

    fun getData() =
        flow {
            emit(
                listOf(
                    Emissions.UpTo145,
                    Emissions.UpTo155,
                    Emissions.UpTo165,
                    Emissions.UpTo175,
                    Emissions.UpTo185,
                    Emissions.UpTo195,
                    Emissions.UpTo205,
                    Emissions.UpTo215,
                    Emissions.UpTo225,
                    Emissions.UpTo235,
                    Emissions.UpTo245,
                    Emissions.UpTo255,
                    Emissions.MoreThan255
                )
            )
        }

}