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
    val categoryRecipe: String,
    val textRecipe: String,
    val isFavorite: Boolean = false,
) : Parcelable {

    @Parcelize
    enum class Category(val category: String) : Parcelable {
        European("Европейская кухня"),
        Asian("Азиатская кухня"),
        Panasian("Паназиатская кухня"),
        Eastern("Восточная кухня"),
        American("Американская кухня"),
        Russian("Русская кухня",),
        Mediterranean("Средиземноморская кухня")
    }
}

