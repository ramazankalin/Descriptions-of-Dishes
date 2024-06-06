package com.example.descriptionsofdishes.service

import com.example.descriptionsofdishes.model.CategoryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodAPIService {

    private val BASE_URL = "https://www.themealdb.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    fun getData(): Call<CategoryResponse> {
        return api.getFoods()
    }
}
