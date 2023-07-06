package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import kotlinx.coroutines.flow.flow

class RegistrationRepository {

    fun getRegistrationData() =
        flow { emit(registrationData) }

    fun getLpgAnnualData() =
        flow { emit(lpgAnnualAdd) }

    fun getAnnualData() =
        flow { emit(annualData) }

    companion object {
        private val registrationData = listOf(
            Pair(
                0,
                listOf(
                    Pair(Pair(EnginePower.UpTo70, 8), 0f),
                    Pair(Pair(EnginePower.UpTo85, 10), 0f),
                    Pair(Pair(EnginePower.UpTo100, 11), 197f),
                    Pair(Pair(EnginePower.UpTo110, 14), 569f),
                    Pair(Pair(EnginePower.UpTo120, 15), 941f),
                    Pair(Pair(EnginePower.UpTo155, 17), 2180f),
                    Pair(Pair(EnginePower.MoreThan155, Int.MAX_VALUE), 4659f),
                )
            ),
            Pair(
                1,
                listOf(
                    Pair(Pair(EnginePower.UpTo70, 8), 61.5f),
                    Pair(Pair(EnginePower.UpTo85, 10), 61.5f),
                    Pair(Pair(EnginePower.UpTo100, 11), 177.3f),
                    Pair(Pair(EnginePower.UpTo110, 14), 512.1f),
                    Pair(Pair(EnginePower.UpTo120, 15), 846.9f),
                    Pair(Pair(EnginePower.UpTo155, 17), 1962f),
                    Pair(Pair(EnginePower.MoreThan155, Int.MAX_VALUE), 4193f),
                )
            ),
            Pair(
                2,
                listOf(
                    Pair(Pair(EnginePower.UpTo70, 8), 61.5f),
                    Pair(Pair(EnginePower.UpTo85, 10), 61.5f),
                    Pair(Pair(EnginePower.UpTo100, 11), 157.6f),
                    Pair(Pair(EnginePower.UpTo110, 14), 455.2f),
                    Pair(Pair(EnginePower.UpTo120, 15), 752.8f),
                    Pair(Pair(EnginePower.UpTo155, 17), 1744f),
                    Pair(Pair(EnginePower.MoreThan155, Int.MAX_VALUE), 3727.2f),
                )
            ),
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