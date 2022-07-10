package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.FragmentCreateBinding
import ru.netology.nerecipe.dto.Category

import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipeCreateFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeViewModel>()
    private lateinit var categoryRecipeNumber : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCreateBinding.inflate(layoutInflater, container, false).also { binding ->

        binding.buttonSave.setOnClickListener {
            onSaveButtonClicked(binding)
        }

        binding.categoryRecipeCheckBoxOne.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.checkBoxEuropean -> categoryRecipeNumber = Category.European.nameCategory
                R.id.checkBoxAsian -> categoryRecipeNumber = Category.Asian.nameCategory
                R.id.checkBoxPanasian -> categoryRecipeNumber = Category.Panasian.nameCategory
                R.id.checkBoxEastern -> categoryRecipeNumber = Category.Eastern.nameCategory
                R.id.checkBoxAmerican -> categoryRecipeNumber = Category.American.nameCategory
                R.id.checkBoxRussian -> categoryRecipeNumber = Category.Russian.nameCategory
                R.id.checkBoxMediterranean -> categoryRecipeNumber = Category.Mediterranean.nameCategory
            }
        }

        binding.buttonSave.setOnClickListener {
            onSaveButtonClicked(binding)
        }
    }.root

    private fun onSaveButtonClicked(binding: FragmentCreateBinding) {

        val title = binding.title.text.toString()
        val authorName = binding.authorName.text.toString()
        val textRecipe = binding.textRecipe.text.toString()

        if (!emptyCheckUpdateWarning(
                title = title,
                authorName = authorName,
                textRecipe = textRecipe,
                categoryRecipe = categoryRecipeNumber,
            )
        ) return

        viewModel.onSaveClicked(
            title = title,
            authorNam = authorName,
            categoryRecipe = categoryRecipeNumber,
            textRecipe = textRecipe
        )
        findNavController().popBackStack()
    }

    private fun emptyCheckUpdateWarning(
        title: String,
        authorName: String,
        textRecipe: String,
        categoryRecipe: String
    ): Boolean {
        return if (title.isBlank() || authorName.isBlank() || textRecipe.isBlank() || categoryRecipe.isBlank()) {
            Toast.makeText(activity, "All fields must be filled in", Toast.LENGTH_LONG).show()
            false
        } else true
    }

}