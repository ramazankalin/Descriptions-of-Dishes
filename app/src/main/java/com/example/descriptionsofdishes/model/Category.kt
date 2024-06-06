package com.example.descriptionsofdishes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id2 : Int = 0,
    @ColumnInfo("idCategory")
    @SerializedName("idCategory")
    val id: String,
    @ColumnInfo("strCategory")
    @SerializedName("strCategory")
    val title: String,
    @ColumnInfo("strCategoryThumb")
    @SerializedName("strCategoryThumb")
    val imageUrl: String,
    @ColumnInfo("strCategoryDescription")
    @SerializedName("strCategoryDescription")
    val description: String
) : Parcelable




