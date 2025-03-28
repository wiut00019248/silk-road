package com.example.mad_cw_19248.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_cw_19248.R
import com.example.mad_cw_19248.data.models.MedicineRequest
import com.example.mad_cw_19248.ui.views.MedicineViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = colorResource(id = R.color.blue),
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicineScreen(
    navController: NavController,
    viewModel: MedicineViewModel = MedicineViewModel()
) {
    var name by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }
    var serialNumber by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf<Date?>(null) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    var showDatePicker by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_medicine),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.Start)
            )

            CustomTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Enter the name here"
            )

            CustomTextField(
                value = dosage,
                onValueChange = { dosage = it },
                placeholder = "Dosage"
            )

            CustomTextField(
                value = ingredients,
                onValueChange = { ingredients = it },
                placeholder = "Enter the composition here"
            )

            CustomTextField(
                value = manufacturer,
                onValueChange = { manufacturer = it },
                placeholder = "Enter the manufacturer here"
            )

            CustomTextField(
                value = serialNumber,
                onValueChange = { serialNumber = it },
                placeholder = "Enter the serial number here"
            )

            CustomTextField(
                value = price,
                onValueChange = { price = it },
                placeholder = "Price"
            )

            CustomTextField(
                value = quantity,
                onValueChange = { quantity = it },
                placeholder = "Quantity"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = expiryDate?.let {
                        SimpleDateFormat("MM/dd/yyyy", Locale.US).format(it)
                    } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Expiry Date") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = "Select Expiry Date"
                            )
                        }
                    }
                )
            }

            CustomTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = "Enter the description here"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val medicine = MedicineRequest(
                        name = name,
                        dosage = dosage.toDoubleOrNull() ?: 0.0,
                        ingredients = ingredients,
                        manufacturer = manufacturer,
                        serialNumber = serialNumber,
                        price = price,
                        quantity = quantity.toIntOrNull() ?: 0,
                        expirationDate = expiryDate ?: Date(),
                        description = description
                    )

                    viewModel.addNewMedicine(medicine)
                    navController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Add New")
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePickerState.selectedDateMillis?.let { millis ->
                                    expiryDate = Date(millis)
                                }
                                showDatePicker = false
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDatePicker = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState,
                        title = {
                            Text(
                                text = "Select Expiry Date",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}
