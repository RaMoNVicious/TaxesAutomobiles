package cg.viciousconcepts.taxesautomobiles.models.domain

import androidx.annotation.StringRes
import cg.viciousconcepts.taxesautomobiles.R

enum class EnginePower(
    val min: Int = 0,
    val max: Int = Int.MAX_VALUE,
    @StringRes val stringId: Int
) {
    UpTo70(
        max = 70,
        stringId = R.string.engine_power_up_to_70
    ),
    UpTo85(
        min = 71,
        max = 85,
        stringId = R.string.engine_power_up_to_85
    ),
    UpTo100(
        min = 86,
        max = 100,
        stringId = R.string.engine_power_up_to_100
    ),
    UpTo110(
        min = 101,
        max = 110,
        stringId = R.string.engine_power_up_to_110
    ),
    UpTo120(
        min = 111,
        max = 120,
        stringId = R.string.engine_power_up_to_120
    ),
    UpTo155(
        min = 121,
        max = 155,
        stringId = R.string.engine_power_up_to_155
    ),
    MoreThan155(
        min = 156,
        stringId = R.string.engine_power_more_than_155
    )
}