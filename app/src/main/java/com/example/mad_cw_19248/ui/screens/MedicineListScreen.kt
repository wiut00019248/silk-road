package com.example.mad_cw_19248.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_cw_19248.R
import com.example.mad_cw_19248.data.api.Request
import com.example.mad_cw_19248.data.models.Medicine
import com.example.mad_cw_19248.ui.views.MedicineViewModel

@Composable
fun MedicineListScreen(
    navController: NavController,
    viewModel: MedicineViewModel = MedicineViewModel()
) {
    val medicines by viewModel.medicines.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                modifier = Modifier
                    .padding(10.dp)
                    .offset(y = 20.dp),
                containerColor = colorResource(id = R.color.blue)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Medicine",
                    tint = Color.White,
                )
            }
        }
    ) { paddingValues ->
        if (medicines.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(medicines) {
                    MedicineCard(it, viewModel)
                }
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine, viewModel: MedicineViewModel) {
    var quantity by remember { mutableStateOf(medicine.quantity) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            title = { Text("Delete Medicine") },
            text = {
                Text("Are you sure you want to delete ${medicine.name}?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteMedicine(medicine.id)
                        showDeleteDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") }
            },
            onDismissRequest = { showDeleteDialog = false }
        )
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, colorResource(id = R.color.blue), RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Text(text = medicine.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Text(text = "${medicine.price} ${stringResource(R.string.currency)}", fontSize = 16.sp, color = Color.Gray)

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                ) {
                    Text(stringResource(R.string.more), color = Color.White, fontSize = 16.sp)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorResource(id = R.color.blue))
                            .clickable {
                                quantity--;
                                viewModel.updateMedicine(medicine.id, Request(
                                    name = medicine.name,
                                    description = medicine.description,
                                    ingredients = medicine.ingredients,
                                    manufacturer = medicine.manufacturer,
                                    quantity = quantity,
                                    serialNumber = medicine.serialNumber,
                                    price = medicine.price,
                                    expirationDate = medicine.expirationDate,
                                    dosage = medicine.dosage
                                ))
                            }
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Remove,
                            contentDescription = "Remove",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Text("$quantity", fontSize = 18.sp, fontWeight = FontWeight.Medium)

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorResource(id = R.color.blue))
                            .clickable {
                                quantity++;
                                viewModel.updateMedicine(medicine.id, Request(
                                    name = medicine.name,
                                    description = medicine.description,
                                    ingredients = medicine.ingredients,
                                    manufacturer = medicine.manufacturer,
                                    quantity = quantity,
                                    serialNumber = medicine.serialNumber,
                                    price = medicine.price,
                                    expirationDate = medicine.expirationDate,
                                    dosage = medicine.dosage
                                ))
                            }
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(27.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Red)
                        .clickable { showDeleteDialog = true }
                        .padding(8.dp)
                        .align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}