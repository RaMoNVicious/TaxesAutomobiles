package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.Emissions
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import kotlinx.coroutines.flow.flow

class RegistrationRepository {

    fun getRegistrationData() =
        flow { emit(registrationData) }

    fun getEmissionData() =
        flow { emit(emissionData) }

    fun getLpgAnnualData() =
        flow { emit(lpgAnnualAdd) }

    fun getAnnualData() =
        flow { emit(annualData) }

    companion object {
        private val registrationData = listOf(
            Pair(
                0,
                listOf(
                    Pair(Pair(EnginePower.UpTo70, 8), 61.5f),
                    Pair(Pair(EnginePower.UpTo85, 10), 123.0f),
                    Pair(Pair(EnginePower.UpTo100, 11), 495.0f),
                    Pair(Pair(EnginePower.UpTo110, 14), 867.0f),
                    Pair(Pair(EnginePower.UpTo120, 15), 1239.0f),
                    Pair(Pair(EnginePower.UpTo155, 17), 2478.0f),
                    Pair(Pair(EnginePower.MoreThan155, Int.MAX_VALUE), 4957.0f),
                )
            ),
            Pair(
                1,
                listOf(
                    Pair(Pair(EnginePower.UpTo70, 8), 61.5f),
                    Pair(Pair(EnginePower.UpTo85, 10), 110.7f),
                    Pair(Pair(EnginePower.UpTo100, 11), 445.5f),
                    Pair(Pair(EnginePower.UpTo110, 14), 780.3f),
                    Pair(Pair(EnginePower.UpTo120, 15), 1115.1f),
                    Pair(Pair(EnginePower.UpTo155, 17), 2230.2f),
                    Pair(Pair(EnginePower.MoreThan155, Int.MAX_VALUE), 4461.3f),
                )
            ),
            Pair(
                2,
                listOf(
                    Pair(Pair(EnginePower.UpTo70, 8), 61.5f),
                    Pair(Pair(EnginePower.UpTo85, 10), 98.4f),
                    Pair(Pair(EnginePower.UpTo100, 11), 396.0f),
                    Pair(Pair(EnginePower.UpTo110, 14), 693.6f),
                    Pair(Pair(EnginePower.UpTo120, 15), 991.2f),
                    Pair(Pair(EnginePower.UpTo155, 17), 1982.4f),
                    Pair(Pair(EnginePower.MoreThan155, Int.MAX_VALUE), 3965.6f),
                )
            ),
        )

        private val emissionData = listOf(
            Pair(Emissions.UpTo145, 0f),
            Pair(Emissions.UpTo155, 100f),
            Pair(Emissions.UpTo165, 175f),
            Pair(Emissions.UpTo175, 250f),
            Pair(Emissions.UpTo185, 375f),
            Pair(Emissions.UpTo195, 500f),
            Pair(Emissions.UpTo205, 600f),
            Pair(Emissions.UpTo215, 700f),
            Pair(Emissions.UpTo225, 1000f),
            Pair(Emissions.UpTo235, 1200f),
            Pair(Emissions.UpTo245, 1500f),
            Pair(Emissions.UpTo255, 2000f),
            Pair(Emissions.MoreThan255, 2500f),
        )

        private val lpgAnnualAdd = listOf(
            Pair(7, 89.16f),
            Pair(13, 148.68f),
            Pair(Int.MAX_VALUE, 208.2f),
        )

        private val annualData = listOf(
            Pair(Pair(0..750, 4), 97.68f),
            Pair(Pair(751..950, 5), 122.23f),
            Pair(Pair(951..1150, 6), 176.62f),
            Pair(Pair(1151..1350, 7), 230.87f),
            Pair(Pair(1351..1550, 8), 285.38f),
            Pair(Pair(1551..1750, 9), 340.03f),
            Pair(Pair(1751..1950, 10), 394.02f),
            Pair(Pair(1951..2150, 11), 511.24f),
            Pair(Pair(2151..2350, 12), 628.58f),
            Pair(Pair(2351..2550, 13), 745.54f),
            Pair(Pair(2551..2750, 14), 862.88f),
            Pair(Pair(2751..3050, 15), 980.10f),
            Pair(Pair(3051..3250, 16), 1283.70f),
            Pair(Pair(3251..3450, 17), 1587.56f),
            Pair(Pair(3451..3650, 18), 1891.43f),
            Pair(Pair(3651..3950, 19), 2194.63f),
            Pair(Pair(3951..Int.MAX_VALUE, 20), 2498.50f),
            //Pair(Pair(3951..4150, 20), 2498.50f),
        )
    }
}