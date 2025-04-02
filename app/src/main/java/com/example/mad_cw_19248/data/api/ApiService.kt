package com.example.mad_cw_19248.data.api

import com.example.mad_cw_19248.data.models.Medicine
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.Date

data class Response(
    @SerializedName("code") val code: Int = 0,
    @SerializedName("status") val status: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: List<Medicine> = emptyList()
)

data class Request(
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

interface ApiService {
    @GET("records/all")
    suspend fun getAllMedicines(): Response

    @POST("records")
    suspend fun addMedicine(@Body medicine: Request): Response

    @PUT("records/{id}")
    suspend fun updateMedicineById(@Path("id") id: Int, @Body medicine: Request): Response

    @DELETE("records/{id}")
    suspend fun deleteMedicineById(@Path("id") id: Int): Response
}