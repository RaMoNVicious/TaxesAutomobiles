package cg.viciousconcepts.taxesautomobiles.ui.tune

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.FragmentTuneBinding
import cg.viciousconcepts.taxesautomobiles.models.domain.TaxInput
import cg.viciousconcepts.taxesautomobiles.ui.main.MainFragment.Companion.VALUE_FOR_RESULT
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
            TaxInput.Age -> {
                _binding.txtTitle.text = getString(
                    R.string.selection_title,
                    getString(R.string.age_title)
                )
                viewModel.age.observe(viewLifecycleOwner) { items ->
                    _binding.rlrValues.setItems(
                        items = items,
                        selected = bundle.getInt(ARGUMENT_VALUE_SELECTED),
                        valueFormat = { value ->
                            when {
                                value <= items[1] -> getString(R.string.age_value_lower)

                                value >= items[items.count() - 2] ->
                                    getString(
                                        R.string.age_value_greater,
                                        items[items.count() - 2]
                                    )

                                else -> getString(R.string.age_value, value)
                            }
                        },
                        tikHigh = 10,
                        tikMiddle = 5,
                        onSelected = { value ->
                            result.putSerializable(ARGUMENT_VALUE_SELECTED, value)
                        }
                    )
                }
                viewModel.getAge()
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
                            when {
                                value <= items[1] ->
                                    getString(
                                        R.string.engine_size_value_lower,
                                        items[1]
                                    )

                                value >= items[items.count() - 2] ->
                                    getString(
                                        R.string.engine_size_value_greater,
                                        items[items.count() - 2]
                                    )

                                else ->
                                    getString(
                                        R.string.engine_size_value,
                                        value
                                    )
                            }
                        },
                        tikHigh = 1000,
                        tikMiddle = 500,
                        onSelected = { value ->
                            result.putSerializable(ARGUMENT_VALUE_SELECTED, value)
                        }
                    )
                }
                viewModel.getEngineSizes()
            }
            TaxInput.Children -> {
                _binding.txtTitle.text = getString(
                    R.string.selection_title,
                    getString(R.string.children_title)
                )
                viewModel.children.observe(viewLifecycleOwner) { items ->
                    _binding.rlrValues.setItems(
                        items = items.map { it },
                        selected = bundle.getInt(ARGUMENT_VALUE_SELECTED),
                        valueFormat = { value ->
                            when {
                                value <= items[1] ->
                                    getString(R.string.children_value_lower)

                                value >= items[items.count() - 2] ->
                                    getString(R.string.children_value_greater)

                                else ->
                                    getString(
                                        R.string.children_value,
                                        value
                                    )
                            }
                        },
                        tikHigh = 10,
                        tikMiddle = 2,
                        onSelected = { value ->
                            result.putSerializable(ARGUMENT_VALUE_SELECTED, value)
                        }
                    )
                }
                viewModel.getChildren()
            }
            else -> {}
        }

        _binding.btnDone.setOnClickListener {
            setFragmentResult(VALUE_FOR_RESULT, result)
            dismiss()
        }
    }

    companion object {
        const val ARGUMENT_VALUE_TYPE = "ARGUMENT_VALUE_TYPE"
        const val ARGUMENT_VALUE_SELECTED = "ARGUMENT_VALUE_SELECTED"
    }
}