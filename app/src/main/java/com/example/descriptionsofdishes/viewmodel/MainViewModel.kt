package com.example.descriptionsofdishes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.descriptionsofdishes.database.CategoryDao
import com.example.descriptionsofdishes.database.CategoryDatabase
import com.example.descriptionsofdishes.model.Category
import com.example.descriptionsofdishes.model.CategoryResponse
import com.example.descriptionsofdishes.service.FoodAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val foodAPI = FoodAPIService()

    val foodData = MutableLiveData<List<Category>>()
    val foodLoad = MutableLiveData<Boolean>()
    val foodError = MutableLiveData<Boolean>()

    private var categoryDatabase : CategoryDatabase? = null
    private var categoryDao : CategoryDao? = null
    val category = MutableLiveData<Category>()

    init {
        categoryDatabase = CategoryDatabase.getInstance(application)
        categoryDao = categoryDatabase?.categoryDao()
    }

    fun getDataFromAPI(){
        foodLoad.value = true

        foodAPI.getData().enqueue(object: Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                response.body()?.let {
                    foodData.value = it.categories
                }
                foodLoad.value = false
                foodError.value = false
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                foodLoad.value = false
                foodError.value = true
                Log.e("RetrofitError",t.message.toString())
            }
        })
    }

    fun insertAll(list: List<Category>) = viewModelScope.launch {
        categoryDao?.insertAll(list)
    }

    fun findByName(name : String) = viewModelScope.launch {
        category.value = categoryDao?.findByName(name)
    }
}
