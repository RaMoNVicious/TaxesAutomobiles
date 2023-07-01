package cg.viciousconcepts.taxesautomobiles.models.domain

import androidx.annotation.StringRes
import cg.viciousconcepts.taxesautomobiles.R

enum class Region(@StringRes val stringId: Int, val enable: Boolean) {
    Walloon (R.string.region_walloon, true),
    Flemish (R.string.region_flemish, false),
    Brussels (R.string.region_brussels, false),
}