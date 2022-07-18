package ru.netology.nerecipe.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "recipes")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "authorName")
    val authorName: String,
    @ColumnInfo(name = "categoryRecipe")
    val categoryRecipe: Recipe.Category,
    @ColumnInfo(name = "textRecipe")
    val textRecipe: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,
)