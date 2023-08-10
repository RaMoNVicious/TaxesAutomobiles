package cg.viciousconcepts.taxesautomobiles.models.domain

data class EngineSize(
    val from: Int = 0,
    val to: Int = Int.MAX_VALUE,
    val cv: Byte = Byte.MIN_VALUE
)
