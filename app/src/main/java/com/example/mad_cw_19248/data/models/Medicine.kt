package com.example.mad_cw_19248.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Medicine(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("phone") val ingredients: String = "",
    @SerializedName("url") val manufacturer: String = "",
    @SerializedName("age") val quantity: Int = 0,
    @SerializedName("color") val serialNumber: String = "",
    @SerializedName("price") val price: String = "",
    @SerializedName("date") val expirationDate: Date = Date(),
    @SerializedName("double_one") val dosage: Double = 0.0,
)