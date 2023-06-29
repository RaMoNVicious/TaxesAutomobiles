package cg.viciousconcepts.taxesautomobiles.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cg.viciousconcepts.taxesautomobiles.models.domain.Tax
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _taxInput: MutableLiveData<Tax> = MutableLiveData<Tax>()
    val taxInput: LiveData<Tax> = _taxInput

    private val _taxRegistration: MutableLiveData<UInt> = MutableLiveData<UInt>()
    val taxRegistration: LiveData<UInt> = _taxRegistration

    private val _taxAnnual: MutableLiveData<UInt> = MutableLiveData<UInt>()
    val taxAnnual: LiveData<UInt> = _taxAnnual


    fun getTaxes() {
        viewModelScope.launch(Dispatchers.IO) {
            ensureActive()

            val tax = Tax()

            _taxInput.postValue(tax)
            _taxRegistration.postValue(tax.taxRegistration())
            _taxAnnual.postValue(tax.taxRegistration())
        }
    }
}