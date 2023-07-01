package cg.viciousconcepts.taxesautomobiles.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cg.viciousconcepts.taxesautomobiles.models.domain.Emissions
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Region
import cg.viciousconcepts.taxesautomobiles.models.domain.Tax
import cg.viciousconcepts.taxesautomobiles.models.domain.VehicleType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _taxInput: MutableLiveData<Tax> = MutableLiveData<Tax>()
    val taxInput: LiveData<Tax> = _taxInput

    private val _regions: MutableLiveData<List<Region>> = MutableLiveData<List<Region>>()
    val regions: LiveData<List<Region>> = _regions;


    init {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            // TODO: get from preferences
            val tax = Tax()

            _taxInput.postValue(tax)
        }
    }

    private fun tax() = _taxInput.value ?: Tax()

    fun updateTax(value: Region) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _taxInput.postValue(
                tax().copy(region = value)
            )
        }
    }

    fun updateTax(value: VehicleType) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _taxInput.postValue(
                tax().copy(vehicleType = value)
            )
        }
    }

    fun updateTax(value: EngineType) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _taxInput.postValue(
                tax().copy(engineType = value)
            )
        }
    }

    fun updateTax(value: EnginePower) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _taxInput.postValue(
                tax().copy(enginePower = value)
            )
        }
    }

    fun updateTax(value: Emissions) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _taxInput.postValue(
                tax().copy(emissions = value)
            )
        }
    }
}