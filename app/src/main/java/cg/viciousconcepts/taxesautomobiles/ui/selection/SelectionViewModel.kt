package cg.viciousconcepts.taxesautomobiles.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cg.viciousconcepts.taxesautomobiles.models.domain.Emission
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Region
import cg.viciousconcepts.taxesautomobiles.models.domain.VehicleType
import cg.viciousconcepts.taxesautomobiles.repositories.EmissionRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EnginePowerRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EngineTypeRepository
import cg.viciousconcepts.taxesautomobiles.repositories.RegionRepository
import cg.viciousconcepts.taxesautomobiles.repositories.VehicleTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class SelectionViewModel(
    private val regionRepository: RegionRepository,
    private val vehicleTypeRepository: VehicleTypeRepository,
    private val engineTypeRepository: EngineTypeRepository,
    private val enginePowerRepository: EnginePowerRepository,
    private val emissionsRepository: EmissionRepository,
) : ViewModel() {

    private val _regions: MutableLiveData<List<Region>> = MutableLiveData<List<Region>>()
    val regions: LiveData<List<Region>> = _regions

    private val _vehicleTypes: MutableLiveData<List<VehicleType>> = MutableLiveData<List<VehicleType>>()
    val vehicleTypes: LiveData<List<VehicleType>> = _vehicleTypes

    private val _engineTypes: MutableLiveData<List<EngineType>> = MutableLiveData<List<EngineType>>()
    val engineTypes: LiveData<List<EngineType>> = _engineTypes

    private val _enginePower: MutableLiveData<List<EnginePower>> = MutableLiveData<List<EnginePower>>()
    val enginePower: LiveData<List<EnginePower>> = _enginePower

    private val _emissions: MutableLiveData<List<Emission>> = MutableLiveData<List<Emission>>()
    val emissions: LiveData<List<Emission>> = _emissions

    fun getRegions() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            regionRepository
                .getRegions()
                .collect { _regions.postValue(it) }
        }
    }

    fun getVehicleType() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            vehicleTypeRepository
                .getVehicleType()
                .collect { _vehicleTypes.postValue(it) }
        }
    }

    fun getEngineType() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            engineTypeRepository
                .getEngineSizes()
                .collect { _engineTypes.postValue(it) }
        }
    }

    fun getEnginePower() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            enginePowerRepository
                .getData()
                .collect { _enginePower.postValue(it) }
        }
    }

    fun getEmissions() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            emissionsRepository
                .getData()
                .collect { _emissions.postValue(it) }
        }
    }
}