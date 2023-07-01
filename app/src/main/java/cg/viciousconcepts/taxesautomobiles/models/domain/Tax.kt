package cg.viciousconcepts.taxesautomobiles.models.domain

data class Tax(
    val region: Region = Region.Walloon,
    val vehicleType: VehicleType = VehicleType.Car,
    val engineType: EngineType = EngineType.Petrol,
    val age: UInt = 1u,
    val enginePower: EnginePower = EnginePower.UpTo70,
    val engineSize: UInt = 1000u,
    val emissions: Emissions = Emissions.UpTo145,
    val children: UInt = 0u
) {
    fun taxRegistration() : UInt {
        return 2345u
    }

    fun taxAnnual() : UInt {
        return 1234u
    }
}