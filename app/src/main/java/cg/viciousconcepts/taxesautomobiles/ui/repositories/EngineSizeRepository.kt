package cg.viciousconcepts.taxesautomobiles.ui.repositories

import android.util.Range

class EngineSizeRepository {

    fun getEngineSize() =
        (ENGINE_SIZE_MIN..ENGINE_SIZE_MAX step ENGINE_SIZE_STEP).toList()

    fun getEngineCV(): List<Pair<IntRange, Int>> = listOf(
        Pair(0..750, 4),
        Pair(751..950, 5),
        Pair(951..1150, 6),
        Pair(1151..1350, 7),
        Pair(1351..1550, 8),
        Pair(1551..1750, 9),
        Pair(1751..1950, 10),
        Pair(1951..2150, 11),
        Pair(2151..2350, 12),
        Pair(2351..2550, 13),
        Pair(2551..2750, 14),
        Pair(2751..3050, 15),
        Pair(3051..3250, 16),
        Pair(3251..3450, 17),
        Pair(3451..3650, 18),
        Pair(3651..3950, 19),
        Pair(3951..4150, 20),
    )


    companion object {
        private const val ENGINE_SIZE_MIN = 400
        private const val ENGINE_SIZE_MAX = 7600
        private const val ENGINE_SIZE_STEP = 100
    }

}