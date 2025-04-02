package com.example.mad_cw_19248.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_19248.data.api.Request
import com.example.mad_cw_19248.data.api.RetrofitInstance
import com.example.mad_cw_19248.data.models.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MedicineViewModel : ViewModel() {
    private val _medicines = MutableStateFlow<List<Medicine>>(emptyList())
    val medicines: StateFlow<List<Medicine>> = _medicines.asStateFlow()

    init {
        fetchAllMedicines()
    }

    private fun fetchAllMedicines() {
        viewModelScope.launch {
            try {
                _medicines.value = RetrofitInstance.api.getAllMedicines().data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addNewMedicine(medicine: Request) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.addMedicine(medicine)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateMedicine(id: Int, medicine: Request) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updateMedicineById(id, medicine)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteMedicine(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteMedicineById(id)
                _medicines.value = _medicines.value.filter { it.id != id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}