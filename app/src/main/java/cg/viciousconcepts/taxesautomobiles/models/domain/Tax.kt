package cg.viciousconcepts.taxesautomobiles.models.domain

import java.io.Serializable

data class Tax(
    val region: Region = Region.Walloon,
    val vehicleType: VehicleType = VehicleType.Car,
    val engineType: EngineType = EngineType.Petrol,
    val age: Int = 4,
    val enginePower: EnginePower = EnginePower.UpTo110,
    val engineSize: Int = 2000,
    val emission: Emission = Emission(),
    val children: Int = 0
) : Serializable