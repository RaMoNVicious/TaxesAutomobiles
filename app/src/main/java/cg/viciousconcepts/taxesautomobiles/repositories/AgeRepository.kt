package cg.viciousconcepts.taxesautomobiles.repositories

import kotlinx.coroutines.flow.flow

class AgeRepository {
    fun getAges() =
        flow {
            emit(
                (AGE_MIN..AGE_MAX step AGE_STEP).toList()
            )
        }

    companion object {
        private const val AGE_MIN = -1
        private const val AGE_MAX = 31
        private const val AGE_STEP = 1
    }
}