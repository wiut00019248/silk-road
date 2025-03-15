package com.example.mad_cw_19248.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Medicine(
    val id: Int,
    @SerializedName("student_id")
    val studentId: String,
    @SerializedName("title")
    val name: String,
    val description: String,
    @SerializedName("phone")
    val ingredients: String,
    @SerializedName("url")
    val manufacturer: String,
    @SerializedName("age")
    val quantity: Int,
    @SerializedName("is_it_true")
    val isItTrue: String,
    @SerializedName("is_it_really_true")
    val isItReallyTrue: String,
    @SerializedName("color")
    val serialNumber: String,
    val size: String,
    val price: String,
    val type: String,
    @SerializedName("date")
    val expirationDate: Date,
    @SerializedName("another_date")
    val anotherDate: String,
    @SerializedName("integer_one")
    val integerOne: String,
    @SerializedName("integer_two")
    val integerTwo: String,
    @SerializedName("integer_three")
    val integerThree: String,
    @SerializedName("integer_four")
    val integerFour: String,
    @SerializedName("integer_five")
    val integerFive: String,
    @SerializedName("integer_six")
    val integerSix: String,
    @SerializedName("integer_seven")
    val integerSeven: String,
    @SerializedName("double_one")
    val dosage: Double,
    @SerializedName("double_two")
    val doubleTwo: String,
    @SerializedName("double_three")
    val doubleThree: String,
    @SerializedName("double_four")
    val doubleFour: String,
    @SerializedName("double_five")
    val doubleFive: String,
    @SerializedName("double_six")
    val doubleSix: String,
    @SerializedName("double_seven")
    val doubleSeven: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("int_list")
    val intList: List<IntItem>,
    @SerializedName("text_list")
    val textList: List<TextItem>,
    @SerializedName("double_list")
    val doubleList: List<DoubleItem>
)