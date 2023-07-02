package cg.viciousconcepts.taxesautomobiles.ui.repositories

class EnginePowerRepository {

    fun getEnginePower() =
        (ENGINE_POWER_MIN..ENGINE_POWER_MAX step ENGINE_SIZE_STEP).toList()

    companion object {
        private const val ENGINE_POWER_MIN = 29
        private const val ENGINE_POWER_MAX = 501
        private const val ENGINE_SIZE_STEP = 1
    }

}