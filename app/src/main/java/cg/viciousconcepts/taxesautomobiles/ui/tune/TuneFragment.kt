package cg.viciousconcepts.taxesautomobiles.ui.tune

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.FragmentTuneBinding
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import cg.viciousconcepts.taxesautomobiles.models.domain.TaxInput
import cg.viciousconcepts.taxesautomobiles.ui.main.MainFragment.Companion.VALUE_FOR_RESULT
import cg.viciousconcepts.taxesautomobiles.ui.selection.SelectionFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TuneFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentTuneBinding

    private val viewModel: TuneViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetPopupTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTuneBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.lifecycleOwner = this

        arguments
            ?.takeIf {
                it.containsKey(ARGUMENT_VALUE_TYPE) && it.containsKey(ARGUMENT_VALUE_SELECTED)
            }
            ?.let { setup(it) }
    }

    private fun setup(bundle: Bundle) {
        val result = Bundle()
        result.putSerializable(ARGUMENT_VALUE_TYPE, bundle.getSerializable(ARGUMENT_VALUE_TYPE))

        when (bundle.getSerializable(ARGUMENT_VALUE_TYPE) as TaxInput) {
            TaxInput.EnginePower -> {
                _binding.txtTitle.text = getString(
                    R.string.selection_title,
                    getString(R.string.engine_power_title)
                )
                viewModel.enginePower.observe(viewLifecycleOwner) { items ->
                    _binding.rlrValues.setItems(
                        items = items.map { it },
                        selected = bundle.getInt(ARGUMENT_VALUE_SELECTED),
                        valueFormat = { value ->
                            formatEnginePower(
                                value,
                                items[1],
                                items[items.count() - 3]
                            )
                        },
                        tikHigh = 50,
                        tikMiddle = 10,
                        onSelected = { value ->
                            result.putSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED, value)
                        }
                    )
                }
                viewModel.getEnginePower()
            }
            TaxInput.EngineSize -> {
                _binding.txtTitle.text = getString(
                    R.string.selection_title,
                    getString(R.string.engine_size_title)
                )
                viewModel.engineSize.observe(viewLifecycleOwner) { items ->
                    _binding.rlrValues.setItems(
                        items = items.map { it },
                        selected = bundle.getInt(ARGUMENT_VALUE_SELECTED),
                        valueFormat = { value ->
                            formatEngineSize(
                                value,
                                items[1],
                                items[items.count() - 3]
                            )
                        },
                        tikHigh = 1000,
                        tikMiddle = 500,
                        onSelected = { value ->
                            result.putSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED, value)
                        }
                    )
                }
                viewModel.getEngineSizes()
            }
            TaxInput.Emission -> {
                _binding.txtTitle.text = getString(
                    R.string.selection_title,
                    getString(R.string.emission_title)
                )
                viewModel.emissions.observe(viewLifecycleOwner) { items ->
                    _binding.rlrValues.setItems(
                        items = items.map { it },
                        selected = bundle.getInt(ARGUMENT_VALUE_SELECTED),
                        valueFormat = { value ->
                            formatEmissions(
                                value,
                                items[1],
                                items[items.count() - 3]
                            )
                        },
                        tikHigh = 50,
                        tikMiddle = 10,
                        onSelected = { value ->
                            result.putSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED, value)
                        }
                    )
                }
                viewModel.getEmissions()
            }
            else -> {}
        }

        _binding.btnDone.setOnClickListener {
            setFragmentResult(VALUE_FOR_RESULT, result)
            dismiss()
        }
    }

    private fun formatEnginePower(value: Int, min: Int, max: Int): String {
        return getString(
            if (value <= min) {
                R.string.engine_power_value_lower
            } else if (value > max) {
                R.string.engine_power_value_greater
            } else {
                R.string.engine_power_value
            },
            value
        )
    }

    private fun formatEngineSize(value: Int, min: Int, max: Int): String {
        return getString(
            if (value <= min) {
                R.string.engine_size_value_lower
            } else if (value > max) {
                R.string.engine_size_value_greater
            } else {
                R.string.engine_size_value
            },
            value
        )
    }

    private fun formatEmissions(value: Int, min: Int, max: Int): String {
        return getString(
            if (value <= min) {
                R.string.emission_value_lower
            } else if (value > max) {
                R.string.emission_value_greater
            } else {
                R.string.emission_value
            },
            value
        )
    }

    companion object {
        const val ARGUMENT_VALUE_TYPE = "ARGUMENT_VALUE_TYPE"
        const val ARGUMENT_VALUE_SELECTED = "ARGUMENT_VALUE_SELECTED"
    }
}