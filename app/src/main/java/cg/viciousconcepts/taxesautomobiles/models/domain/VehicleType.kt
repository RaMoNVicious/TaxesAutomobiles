package cg.viciousconcepts.taxesautomobiles.models.domain

import androidx.annotation.StringRes
import cg.viciousconcepts.taxesautomobiles.R

enum class VehicleType(@StringRes val stringId: Int, val enabled: Boolean = true) {
    Motorcycle (R.string.vehicle_type_moto, false),
    Car (R.string.vehicle_type_car, true),
}