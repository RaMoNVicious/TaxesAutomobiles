package cg.viciousconcepts.taxesautomobiles.ui.repositories

class AgeRepository {
    fun getAges() =
        (AGE_MIN..AGE_MAX step AGE_STEP).toList()

    companion object {
        private const val AGE_MIN = -1
        private const val AGE_MAX = 31
        private const val AGE_STEP = 1
    }
}