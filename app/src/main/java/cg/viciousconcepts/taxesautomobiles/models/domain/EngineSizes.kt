package cg.viciousconcepts.taxesautomobiles.models.domain

data class EngineSizes(
    val min: Int = 0,
    val max: Int = 1000,
    val step: Int = 100
)
