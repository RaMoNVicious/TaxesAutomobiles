package cg.viciousconcepts.taxesautomobiles.ui.main

import android.content.SharedPreferences
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
import cg.viciousconcepts.taxesautomobiles.repositories.TaxUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class MainViewModel(
    private val taxUseCase: TaxUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _taxInput: MutableLiveData<Tax> = MutableLiveData<Tax>()
    val taxInput: LiveData<Tax> = _taxInput

    private val _taxAnnual: MutableLiveData<Float> = MutableLiveData<Float>()
    val taxAnnual: LiveData<Float> = _taxAnnual

    private val _taxRegistration: MutableLiveData<Float> = MutableLiveData<Float>()
    val taxRegistration: LiveData<Float> = _taxRegistration

    init {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            _taxInput.postValue(
                sharedPreferences
                    .getString(SAVED_STATE, null)
                    ?.let { Gson().fromJson(it, Tax::class.java) } ?: Tax.defaultTax
            )
        }
    }

    private fun tax() = _taxInput.value ?: Tax.defaultTax

    private fun save(tax: Tax) {
        _taxInput.postValue(tax)
        sharedPreferences
            .edit()
            .putString(SAVED_STATE, Gson().toJson(tax))
            .apply()
    }

    fun getTaxAnnual() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            taxUseCase
                .getAnnualTax(tax())
                .collect { _taxAnnual.postValue(it) }
        }
    }

    fun getTaxRegistration() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            taxUseCase
                .getRegistrationTax(tax())
                .collect { _taxRegistration.postValue(it) }
        }
    }

    fun updateTax(value: Region) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(region = value))
        }
    }

    fun updateTax(value: VehicleType) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(vehicleType = value))
        }
    }

    fun updateTax(value: EngineType) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(engineType = value))
        }
    }

    fun updateTax(value: EnginePower) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(enginePower = value))
        }
    }

    fun updateTax(value: Emissions) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(emissions = value))
        }
    }

    fun updateAge(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(age = value))
        }
    }

    fun updateEngineSize(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(engineSize = value))
        }
    }

    fun updateChildren(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            save(tax().copy(children = value))
        }
    }

    companion object {
        private const val SAVED_STATE = "SAVED_TAX"
    }
}