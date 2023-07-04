package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.Region
import kotlinx.coroutines.flow.flow

class RegionRepository {

    fun getRegions() =
        flow { emit(listOf(Region.Brussels, Region.Walloon, Region.Flemish)) }
}