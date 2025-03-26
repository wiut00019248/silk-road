package com.example.mad_cw_19248.data.api

import com.example.mad_cw_19248.data.models.Medicine
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("records/all")
    suspend fun getAllMedicines(): ApiResponse

    @DELETE("records/{id}")
    suspend fun deleteMedicineById(@Path("id") id: Int): ApiResponse
}