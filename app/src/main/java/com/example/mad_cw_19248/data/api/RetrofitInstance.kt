package com.example.mad_cw_19248.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val originalUrl = original.url

            val updatedUrl = originalUrl.newBuilder()
                .addQueryParameter("student_id", "00019248")
                .build()

            val request = original.newBuilder()
                .url(updatedUrl)
                .build()

            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)
        .build()

    private val BASE_URL = "https://wiutmadcw.uz/api/v1/"
    private val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}