package cg.viciousconcepts.taxesautomobiles.ui.repositories

class EmissionRepository {

    fun getEmissions() =
        (EMISSIONS_MIN..EMISSIONS_MAX step EMISSIONS_STEP).toList()

    companion object {
        private const val EMISSIONS_MIN = 99
        private const val EMISSIONS_MAX = 301
        private const val EMISSIONS_STEP = 1
    }


}