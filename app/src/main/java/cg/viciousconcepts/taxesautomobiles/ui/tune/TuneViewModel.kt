package cg.viciousconcepts.taxesautomobiles.ui.tune

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cg.viciousconcepts.taxesautomobiles.repositories.AgeRepository
import cg.viciousconcepts.taxesautomobiles.repositories.ChildrenRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EngineSizeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class TuneViewModel(
    private val ageRepository: AgeRepository,
    private val engineSizeRepository: EngineSizeRepository,
    private val childrenRepository: ChildrenRepository
) : ViewModel() {

    private val _age: MutableLiveData<List<Int>> = MutableLiveData()
    val age: LiveData<List<Int>> = _age

    private val _engineSize: MutableLiveData<List<Int>> = MutableLiveData<List<Int>>()
    val engineSize: LiveData<List<Int>> = _engineSize

    private val _children: MutableLiveData<List<Int>> = MutableLiveData()
    val children: LiveData<List<Int>> = _children


    fun getAge() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            ageRepository
                .getData()
                .collect { _age.postValue(it) }
        }
    }

    fun getEngineSizes() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            engineSizeRepository
                .getData()
                .collect { _engineSize.postValue(it) }
        }
    }

    fun getChildren() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            childrenRepository
                .getData()
                .collect { _children.postValue(it) }
        }
    }
}