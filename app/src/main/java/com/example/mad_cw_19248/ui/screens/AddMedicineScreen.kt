package com.example.mad_cw_19248.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.mad_cw_19248.data.models.Medicine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(navController: NavController) {
    var medicine by remember { mutableStateOf(Medicine()) }

    Column {
        Text("Add New Medicine")
        TextField(
            value = medicine.name,
            onValueChange = { name: String -> medicine = medicine.copy(name = name) },
            label = { Text("Name") }
        )
        TextField(
            value = medicine.dosage,
            onValueChange = { dosage: String ->
                val parsed = dosage.toDoubleOrNull() ?: 0.0;
                medicine = medicine.copy(dosage = parsed);
            },
            label = { Text("Dosage") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = medicine.ingredients,
            onValueChange = { ingredients: String -> medicine = medicine.copy(ingredients = ingredients) },
            label = { Text("Ingredients") }
        )
        TextField(
            value = medicine.manufacturer,
            onValueChange = { manufacturer: String -> medicine = medicine.copy(manufacturer = manufacturer) },
            label = { Text("Manufacturer") }
        )
        TextField(
            value = medicine.serialNumber,
            onValueChange = { serialNumber: String -> medicine = medicine.copy(serialNumber = serialNumber) },
            label = { Text("Serial Number") }
        )
    }
}
