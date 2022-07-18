package ru.netology.nerecipe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.adapter.RecipeInteractionListener
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.repository.RecipeRepositoryImpl
import ru.netology.nerecipe.db.AppDb
import ru.netology.nerecipe.repository.RecipeRepository
import ru.netology.nerecipe.settings.SettingsRepository
import ru.netology.nerecipe.settings.SharedPrefsSettingsRepository
import ru.netology.nerecipe.util.Event

class RecipeViewModel(application: Application) : AndroidViewModel(application),
    RecipeInteractionListener {
    
    private val repository: RecipeRepository =
        RecipeRepositoryImpl(dao = AppDb.getInstance(context = application).recipeDao)

    val data by repository::data
    var filterIsActive = false
    val favoriteFragment = Event<String>()
    val createFragment = Event<Unit>()
    val updateRecipeFragment = Event<String?>()
    val singleFragment = Event<Unit>()
    val filterFragment = Event<Unit>()
    val updateRecipe = MutableLiveData<Recipe>(null)
    val singleRecipe = MutableLiveData<Recipe?>(null)
    private val currentRecipe = MutableLiveData<Recipe?>(null)
    private val repositorySettings: SettingsRepository = SharedPrefsSettingsRepository(application)
    private val categoryList = mutableSetOf<Int>()

    override fun saveStateSwitch(key: String, b: Boolean) {
        setupCategories(key, b)
        repositorySettings.saveStateSwitch(key, b)
    }

    override fun getStateSwitch(key: String): Boolean {
        val b = repositorySettings.getStateSwitch(key)
        setupCategories(key, b)
        return b
    }

    override fun clearStateSwitch() {
        repositorySettings.clearStateSwitch().clear().apply()
    }

    private fun setupCategories(key: String, b: Boolean) {
        if (key == Recipe.Category.European.key) {
            if (b) {
                categoryList.add(Recipe.Category.European.id)
            } else {
                categoryList.remove(Recipe.Category.European.id)
            }
        }
        if (key == Recipe.Category.Asian.key) {
            if (b) {
                categoryList.add(Recipe.Category.Asian.id)
            } else {
                categoryList.remove(Recipe.Category.Asian.id)
            }
        }
        if (key == Recipe.Category.Panasian.key) {
            if (b) {
                categoryList.add(Recipe.Category.Panasian.id)
            } else {
                categoryList.remove(Recipe.Category.Panasian.id)
            }
        }
        if (key == Recipe.Category.Eastern.key) {
            if (b) {
                categoryList.add(Recipe.Category.Eastern.id)
            } else {
                categoryList.remove(Recipe.Category.Eastern.id)
            }
        }
        if (key == Recipe.Category.American.key) {
            if (b) {
                categoryList.add(Recipe.Category.American.id)
            } else {
                categoryList.remove(Recipe.Category.American.id)
            }
        }
        if (key == Recipe.Category.Russian.key) {
            if (b) {
                categoryList.add(Recipe.Category.Russian.id)
            } else {
                categoryList.remove(Recipe.Category.Russian.id)
            }
        }
        if (key == Recipe.Category.Mediterranean.key) {
            if (b) {

                categoryList.add(Recipe.Category.Mediterranean.id)
            } else {

                categoryList.remove(Recipe.Category.Mediterranean.id)
            }
        }
        repository.setFilter(categoryList)
    }

    fun clearFilter() {
        repository.getData()
    }

    override fun updateContent(
        id: Long,
        title: String,
        authorNam: String,
        categoryRecipe: Recipe.Category,
        textRecipe: String
    ) {
        val recipe = Recipe(
            id = id,
            title = title,
            authorName = authorNam,
            categoryRecipe = categoryRecipe,
            textRecipe = textRecipe
        )
        repository.save(recipe)
    }

    override fun onRemoveClicked(recipe: Recipe) {
        repository.delete(recipe)
    }

    override fun onEditClicked(recipe: Recipe) {
        updateRecipe.value = recipe
        updateRecipeFragment.call()
    }

    override fun onFavoriteClicked(recipeId: Long) {
        repository.favorite(recipeId)
    }

    override fun onSearchClicked(text: String) {
        repository.searchText(text)
    }

    override fun onCreateClicked() {
        createFragment.call()
    }

     override fun onSaveClicked(title: String, authorNam: String, categoryRecipe: Recipe.Category, textRecipe: String) {

        val recipe = Recipe(
            id = RecipeRepository.NEW_ID,
            title = title,
            authorName = authorNam,
            categoryRecipe = categoryRecipe,
            textRecipe = textRecipe
        )
        repository.save(recipe)
        currentRecipe.value = null
    }

    override fun onSingleRecipeClicked(recipe: Recipe) {
        singleRecipe.value = recipe
        singleFragment.call()
    }

    fun showEuropean(categoryRecipe: String) {
        repository.showEuropean(categoryRecipe)
        filterIsActive = true
    }

    fun showAsian(categoryRecipe: String) {
        repository.showAsian(categoryRecipe)
        filterIsActive = true
    }

    fun showPanasian(categoryRecipe: String) {
        repository.showPanasian(categoryRecipe)
        filterIsActive = true
    }

    fun showEastern(categoryRecipe: String) {
        repository.showEastern(categoryRecipe)
        filterIsActive = true
    }

    fun showAmerican(categoryRecipe: String) {
        repository.showAmerican(categoryRecipe)
        filterIsActive = true
    }

    fun showRussian(categoryRecipe: String) {
        repository.showRussian(categoryRecipe)
        filterIsActive = true
    }

    fun showMediterranean(categoryRecipe: String) {
        repository.showMediterranean(categoryRecipe)
        filterIsActive = true
    }
}