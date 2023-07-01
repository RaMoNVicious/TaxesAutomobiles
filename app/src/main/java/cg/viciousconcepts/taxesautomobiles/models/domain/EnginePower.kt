package cg.viciousconcepts.taxesautomobiles.models.domain

import androidx.annotation.StringRes
import cg.viciousconcepts.taxesautomobiles.R

enum class EnginePower(@StringRes val stringId: Int) {
    UpTo70 (R.string.engine_power_up_to_70),
    UpTo85 (R.string.engine_power_up_to_85),
    UpTo100 (R.string.engine_power_up_to_100),
    UpTo110 (R.string.engine_power_up_to_110),
    UpTo120 (R.string.engine_power_up_to_120),
    UpTo155 (R.string.engine_power_up_to_155),
    MoreThan155 (R.string.engine_power_more_than_155)
}