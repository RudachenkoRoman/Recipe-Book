package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.FragmentFilterBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel


class RecipeFilterFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeViewModel>()
    private lateinit var binding: FragmentFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFilterBinding.inflate(layoutInflater, container, false).also { binding = it

        with(binding) {
            checkBoxEuropean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EUROPEAN)
            checkBoxAsian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_ASIAN)
            checkBoxPanasian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_PAN_ASIAN)
            checkBoxEastern.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EASTERN)
            checkBoxAmerican.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_AMERICAN)
            checkBoxRussian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_RUSSIAN)
            checkBoxMediterranean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_MEDITERANEAN)
        }

        with(binding) {

            checkBoxEuropean.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_EUROPEAN,
                    checkBoxEuropean.isChecked
                )
            }
            checkBoxAsian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_ASIAN,
                    checkBoxAsian.isChecked
                )
            }
            checkBoxPanasian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_PAN_ASIAN,
                    checkBoxPanasian.isChecked
                )
            }
            checkBoxEastern.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_EASTERN,
                    checkBoxEastern.isChecked
                )
            }
            checkBoxAmerican.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_AMERICAN,
                    checkBoxAmerican.isChecked
                )
            }
            checkBoxRussian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_RUSSIAN,
                    checkBoxRussian.isChecked
                )
            }
            checkBoxMediterranean.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_MEDITERANEAN,
                    checkBoxMediterranean.isChecked
                )
            }
        }
        binding.buttonApply.setOnClickListener {
            onApplyButtonClicked(binding)
        }
    }.root

    companion object {
        const val KEY_STATE_SWITCH_EUROPEAN = "european"
        const val KEY_STATE_SWITCH_ASIAN = "asian"
        const val KEY_STATE_SWITCH_PAN_ASIAN = "pan_asian"
        const val KEY_STATE_SWITCH_EASTERN = "eastern"
        const val KEY_STATE_SWITCH_AMERICAN = "american"
        const val KEY_STATE_SWITCH_RUSSIAN = "russian"
        const val KEY_STATE_SWITCH_MEDITERANEAN = "mediterranean"
    }

    private fun onApplyButtonClicked(binding: FragmentFilterBinding) {
        var checkedCount = 0
        val numberOfFilters = 7

        if (!binding.checkBoxEuropean.isChecked) {
            ++checkedCount
            viewModel.showEuropean(getString(R.string.european_type))
        }
        if (!binding.checkBoxAsian.isChecked) {
            ++checkedCount
            viewModel.showAsian(getString(R.string.asian_type))
        }
        if (!binding.checkBoxPanasian.isChecked) {
            ++checkedCount
            viewModel.showPanasian(getString(R.string.panasian_type))
        }
        if (!binding.checkBoxEastern.isChecked) {
            ++checkedCount
            viewModel.showEastern(getString(R.string.eastern_type))
        }
        if (!binding.checkBoxAmerican.isChecked) {
            ++checkedCount
            viewModel.showAmerican(getString(R.string.american_type))
        }
        if (!binding.checkBoxRussian.isChecked) {
            ++checkedCount
            viewModel.showRussian(getString(R.string.russian_type))
        }
        if (!binding.checkBoxMediterranean.isChecked) {
            ++checkedCount
            viewModel.showMediterranean( getString(R.string.russian_type))
        }
        if (checkedCount == numberOfFilters) {
            Toast.makeText(activity, getString(R.string.please_select_at_least_one_option), Toast.LENGTH_LONG).show()
            return
        } else findNavController().popBackStack()
    }
}


