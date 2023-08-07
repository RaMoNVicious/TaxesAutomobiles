package cg.viciousconcepts.taxesautomobiles.models.domain

import java.io.Serializable

data class Tax(
    val region: Region,
    val vehicleType: VehicleType,
    val engineType: EngineType,
    val age: Int,
    val enginePower: EnginePower,
    val engineSize: Int,
    val emissions: Emissions,
    val children: Int
) : Serializable {
    companion object {
        val defaultTax = Tax(
            Region.Walloon,
            VehicleType.Car,
            EngineType.Petrol,
            4,
            EnginePower.UpTo110,
            2000,
            Emissions.UpTo155,
            0,
        )
    }
}