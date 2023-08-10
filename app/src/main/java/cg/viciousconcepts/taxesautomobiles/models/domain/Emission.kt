package cg.viciousconcepts.taxesautomobiles.models.domain

import java.io.Serializable

data class Emission(
    val from: Int = 0,
    val to: Int = 145,
    val value: Int = 0
) : Serializable