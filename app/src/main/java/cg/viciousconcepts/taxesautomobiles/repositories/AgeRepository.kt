package cg.viciousconcepts.taxesautomobiles.repositories

import kotlinx.coroutines.flow.flow

class AgeRepository {
    fun getData() =
        flow {
            emit(
                (VALUE_MIN..VALUE_MAX step VALUE_STEP).toList()
            )
        }

    companion object {
        private const val VALUE_MIN = -1
        private const val VALUE_MAX = 31
        private const val VALUE_STEP = 1
    }
}