package cg.viciousconcepts.taxesautomobiles.models.domain

data class Tax(
    val region: Region = Region.Walloon,
    val vehicleType: VehicleType = VehicleType.Car,
    val engineType: EngineType = EngineType.Petrol,
    val age: Int = 4,
    val enginePower: EnginePower = EnginePower.UpTo100,
    val engineSize: Int = 2500,
    val emissions: Emissions = Emissions.UpTo145,
    val children: Int = 0
)