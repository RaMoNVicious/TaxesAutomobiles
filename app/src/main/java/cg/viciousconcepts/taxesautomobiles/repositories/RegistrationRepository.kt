package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import kotlinx.coroutines.flow.flow

class RegistrationRepository {

    fun getRegistrationData() =
        flow { emit(registrationData) }

    fun getLpgRegistrationData() =
        flow { emit(lpgRegistrationAdd) }

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

        private val lpgRegistrationAdd = listOf(
            Pair(7, 89.16f),
            Pair(13, 148.68f),
            Pair(Int.MAX_VALUE, 208.2f),
        )
    }
}