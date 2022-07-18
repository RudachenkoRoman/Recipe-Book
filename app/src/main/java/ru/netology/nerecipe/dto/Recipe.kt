package ru.netology.nerecipe.dto


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Recipe(
    val id: Long,
    val title: String,
    val authorName: String,
    val categoryRecipe: Category,
    val textRecipe: String,
    val isFavorite: Boolean = false,
) : Parcelable {

    @Parcelize
    enum class Category(
        val key: String,
        val id: Int
        ) : Parcelable{
        European("Европейская кухня", 0),
        Asian("Азиатская кухня", 1),
        Panasian("Паназиатская кухня", 2),
        Eastern("Восточная кухня", 3),
        American("Американская кухня", 4),
        Russian("Русская кухня", 5),
        Mediterranean("Средиземноморская кухня", 6)
    }
}

