package com.example.descriptionsofdishes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.descriptionsofdishes.model.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>


    @Query("SELECT * FROM category WHERE strCategory = :categoryName")
    suspend fun findByName(categoryName: String): Category

    @Insert
    suspend fun insertAll(list: List<Category>)
}