package com.example.mad_cw_19248.data.api

import com.example.mad_cw_19248.data.models.MedicineRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("records/all")
    suspend fun getAllMedicines(): ApiResponse

    @POST("records")
    suspend fun addMedicine(@Body medicine: MedicineRequest): ApiResponse

    @PUT("records/{id}")
    suspend fun updateMedicineById(@Path("id") id: Int, @Body medicine: MedicineRequest): ApiResponse

    @DELETE("records/{id}")
    suspend fun deleteMedicineById(@Path("id") id: Int): ApiResponse
}