package cg.viciousconcepts.taxesautomobiles.ui.repositories

class EngineSizeRepository {

    fun getEngineSize() =
        (ENGINE_SIZE_MIN..ENGINE_SIZE_MAX step ENGINE_SIZE_STEP).toList()

    companion object {
        private const val ENGINE_SIZE_MIN = 400
        private const val ENGINE_SIZE_MAX = 7600
        private const val ENGINE_SIZE_STEP = 100
    }

}