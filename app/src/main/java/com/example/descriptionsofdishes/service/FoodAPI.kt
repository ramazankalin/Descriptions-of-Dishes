package com.example.descriptionsofdishes.service

import com.example.descriptionsofdishes.model.CategoryResponse
import retrofit2.http.GET
import retrofit2.Call

interface FoodAPI {
    @GET("api/json/v1/1/categories.php")
    fun getFoods() : Call<CategoryResponse>
}