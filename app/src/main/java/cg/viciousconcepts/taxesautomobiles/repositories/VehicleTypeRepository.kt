package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.VehicleType
import kotlinx.coroutines.flow.flow

class VehicleTypeRepository {

    fun getVehicleType() =
        flow { emit(listOf(VehicleType.Car, VehicleType.Motorcycle)) }
}