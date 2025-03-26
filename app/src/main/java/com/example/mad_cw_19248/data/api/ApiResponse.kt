package com.example.mad_cw_19248.data.api

import com.example.mad_cw_19248.data.models.Medicine

data class ApiResponse(
    val code: Int = 0,
    val status: String = "",
    val message: String = "",
    val data: List<Medicine>
)