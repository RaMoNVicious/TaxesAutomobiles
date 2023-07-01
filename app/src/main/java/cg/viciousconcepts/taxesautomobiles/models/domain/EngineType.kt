package cg.viciousconcepts.taxesautomobiles.models.domain

import androidx.annotation.StringRes
import cg.viciousconcepts.taxesautomobiles.R

enum class EngineType(@StringRes val stringId: Int) {
    Petrol (R.string.engine_type_petrol),
    Diesel (R.string.engine_type_diesel),
    LPG (R.string.engine_type_lpg),
    Hybrid (R.string.engine_type_hybrid),
    Electric (R.string.engine_type_electric),
}