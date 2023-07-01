package cg.viciousconcepts.taxesautomobiles.models.domain

import androidx.annotation.StringRes
import cg.viciousconcepts.taxesautomobiles.R

enum class Emissions(@StringRes val stringId: Int) {
    UpTo145 (R.string.emission_up_to_145),
    UpTo155 (R.string.emission_up_to_155),
    UpTo165 (R.string.emission_up_to_165),
    UpTo175 (R.string.emission_up_to_175),
    UpTo185 (R.string.emission_up_to_185),
    UpTo195 (R.string.emission_up_to_195),
    UpTo205 (R.string.emission_up_to_205),
    UpTo215 (R.string.emission_up_to_215),
    UpTo225 (R.string.emission_up_to_225),
    UpTo235 (R.string.emission_up_to_235),
    UpTo245 (R.string.emission_up_to_245),
    UpTo255 (R.string.emission_up_to_255),
    MoreThan255 (R.string.emission_more_than_255),
}