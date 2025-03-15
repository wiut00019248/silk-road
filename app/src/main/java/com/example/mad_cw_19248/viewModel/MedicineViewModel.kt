package com.example.mad_cw_19248.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_19248.data.api.RetrofitInstance
import com.example.mad_cw_19248.data.models.Medicine
import kotlinx.coroutines.launch

class MedicineViewModel : ViewModel() {
    private val _medicines = mutableStateOf<List<Medicine>>(emptyList())
    val medicines: State<List<Medicine>> = _medicines

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
}