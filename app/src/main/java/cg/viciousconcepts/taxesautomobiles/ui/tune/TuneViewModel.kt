package cg.viciousconcepts.taxesautomobiles.ui.tune

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cg.viciousconcepts.taxesautomobiles.ui.repositories.AgeRepository
import cg.viciousconcepts.taxesautomobiles.ui.repositories.EmissionRepository
import cg.viciousconcepts.taxesautomobiles.ui.repositories.EnginePowerRepository
import cg.viciousconcepts.taxesautomobiles.ui.repositories.EngineSizeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class TuneViewModel(
    private val ageRepository: AgeRepository,
    private val enginePowerRepository: EnginePowerRepository,
    private val engineSizeRepository: EngineSizeRepository,
    private val emissionRepository: EmissionRepository
) : ViewModel() {

    private val _age: MutableLiveData<List<Int>> = MutableLiveData()
    val age: LiveData<List<Int>> = _age

    private val _enginePower: MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val enginePower: LiveData<List<Int>> = _enginePower

    private val _engineSize: MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val engineSize: LiveData<List<Int>> = _engineSize

    private val _emissions: MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val emissions: LiveData<List<Int>> = _emissions


    fun getAge() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _age.postValue(ageRepository.getAges())
        }
    }

    fun getEnginePower() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _enginePower.postValue(enginePowerRepository.getEnginePower())
        }
    }

    fun getEngineSizes() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _engineSize.postValue(engineSizeRepository.getEngineSize())
        }
    }

    fun getEmissions() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()
            _emissions.postValue(emissionRepository.getEmissions())
        }
    }
}