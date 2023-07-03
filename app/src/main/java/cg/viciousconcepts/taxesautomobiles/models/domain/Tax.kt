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
) {
    private val registrationData: List<Pair<Int, List<Pair<EnginePower, Float>>>> = listOf(
        Pair(
            0,
            listOf(
                Pair(EnginePower.UpTo70, 0f),
                Pair(EnginePower.UpTo85, 0f),
                Pair(EnginePower.UpTo100, 197f),
                Pair(EnginePower.UpTo110, 569f),
                Pair(EnginePower.UpTo120, 941f),
                Pair(EnginePower.UpTo155, 2180f),
                Pair(EnginePower.MoreThan155, 4659f),
            )
        ),
        Pair(
            1,
            listOf(
                Pair(EnginePower.UpTo70, 61.5f),
                Pair(EnginePower.UpTo85, 61.5f),
                Pair(EnginePower.UpTo100, 177.3f),
                Pair(EnginePower.UpTo110, 512.1f),
                Pair(EnginePower.UpTo120, 846.9f),
                Pair(EnginePower.UpTo155, 1962f),
                Pair(EnginePower.MoreThan155, 4193f),
            )
        ),
        Pair(
            2,
            listOf(
                Pair(EnginePower.UpTo70, 61.5f),
                Pair(EnginePower.UpTo85, 61.5f),
                Pair(EnginePower.UpTo100, 137.9f),
                Pair(EnginePower.UpTo110, 398.3f),
                Pair(EnginePower.UpTo120, 658.7f),
                Pair(EnginePower.UpTo155, 1526f),
                Pair(EnginePower.MoreThan155, 3261.3f),
            )
        ),
    )

    fun taxRegistration() : Float {
        return registrationData
            .last { it.first <= age }
            .second
            .last { it.first == enginePower }
            .second
    }

    fun taxAnnual() : UInt {
        return 1234u
    }
}