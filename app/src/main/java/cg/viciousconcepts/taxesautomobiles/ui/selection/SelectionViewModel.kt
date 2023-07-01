package cg.viciousconcepts.taxesautomobiles.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cg.viciousconcepts.taxesautomobiles.models.domain.Emissions
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Region
import cg.viciousconcepts.taxesautomobiles.models.domain.VehicleType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class SelectionViewModel : ViewModel() {

    private val _regions: MutableLiveData<List<Region>> = MutableLiveData<List<Region>>()
    val regions: LiveData<List<Region>> = _regions

    private val _vehicleTypes: MutableLiveData<List<VehicleType>> = MutableLiveData<List<VehicleType>>()
    val vehicleTypes: LiveData<List<VehicleType>> = _vehicleTypes

    private val _engineTypes: MutableLiveData<List<EngineType>> = MutableLiveData<List<EngineType>>()
    val engineTypes: LiveData<List<EngineType>> = _engineTypes

    private val _enginePower: MutableLiveData<List<EnginePower>> = MutableLiveData<List<EnginePower>>()
    val enginePower: LiveData<List<EnginePower>> = _enginePower

    // TODO: Engine size

    private val _emissions: MutableLiveData<List<Emissions>> = MutableLiveData<List<Emissions>>()
    val emissions: LiveData<List<Emissions>> = _emissions

    fun getRegions() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _regions.postValue(listOf(Region.Brussels, Region.Walloon, Region.Flemish))
        }
    }

    fun getVehicleType() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _vehicleTypes.postValue(listOf(VehicleType.Car, VehicleType.Motorcycle))
        }
    }

    fun getEngineType() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _engineTypes.postValue(
                listOf(
                    EngineType.Petrol,
                    EngineType.Diesel,
                    EngineType.LPG,
                    EngineType.Hybrid,
                    EngineType.Electric
                )
            )
        }
    }

    fun getEnginePower() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _enginePower.postValue(
                listOf(
                    EnginePower.UpTo70,
                    EnginePower.UpTo85,
                    EnginePower.UpTo100,
                    EnginePower.UpTo110,
                    EnginePower.UpTo120,
                    EnginePower.UpTo155,
                    EnginePower.MoreThan155,
                )
            )
        }
    }

    fun getEmissions() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _emissions.postValue(
                listOf(
                    Emissions.UpTo145,
                    Emissions.UpTo155,
                    Emissions.UpTo165,
                    Emissions.UpTo175,
                    Emissions.UpTo185,
                    Emissions.UpTo195,
                    Emissions.UpTo205,
                    Emissions.UpTo215,
                    Emissions.UpTo225,
                    Emissions.UpTo235,
                    Emissions.UpTo245,
                    Emissions.UpTo255,
                    Emissions.MoreThan255
                )
            )
        }
    }
}