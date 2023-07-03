package cg.viciousconcepts.taxesautomobiles.ui.selection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.FragmentSelectionBinding
import cg.viciousconcepts.taxesautomobiles.models.domain.Emissions
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Region
import cg.viciousconcepts.taxesautomobiles.models.domain.TaxInput
import cg.viciousconcepts.taxesautomobiles.models.domain.VehicleType
import cg.viciousconcepts.taxesautomobiles.ui.main.MainFragment.Companion.VALUE_FOR_RESULT
import cg.viciousconcepts.taxesautomobiles.ui.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectionFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentSelectionBinding

    private val viewModel: SelectionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetPopupTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.lifecycleOwner = this
        _binding.listItems.removeAllViews()

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
            TaxInput.Region -> {
                viewModel.regions.observe(viewLifecycleOwner) { items ->
                    showList(
                        R.string.region_title,
                        items.map { Pair(it.stringId, it.enable) },
                        items.indexOf(bundle.getSerializable(ARGUMENT_VALUE_SELECTED) as Region)
                    ) { index ->
                        result.putSerializable(ARGUMENT_VALUE_SELECTED, items[index])
                    }
                }
                viewModel.getRegions()
            }
            TaxInput.VehicleType -> {
                viewModel.vehicleTypes.observe(viewLifecycleOwner) { items ->
                    showList(
                        R.string.vehicle_type_title,
                        items.map { Pair(it.stringId, it.enabled) },
                        items.indexOf(bundle.getSerializable(ARGUMENT_VALUE_SELECTED) as VehicleType)
                    ) { index ->
                        result.putSerializable(ARGUMENT_VALUE_SELECTED, items[index])
                    }
                }
                viewModel.getVehicleType()
            }
            TaxInput.EngineType -> {
                viewModel.engineTypes.observe(viewLifecycleOwner) { items ->
                    showList(
                        R.string.engine_type_title,
                        items.map { Pair(it.stringId, true) },
                        items.indexOf(bundle.getSerializable(ARGUMENT_VALUE_SELECTED) as EngineType)
                    ) { index ->
                        result.putSerializable(ARGUMENT_VALUE_SELECTED, items[index])
                    }
                }
                viewModel.getEngineType()
            }
            TaxInput.EnginePower -> {
                viewModel.enginePower.observe(viewLifecycleOwner) { items ->
                    showList(
                        R.string.engine_power_title,
                        items.map { Pair(it.stringId, true) },
                        items.indexOf(bundle.getSerializable(ARGUMENT_VALUE_SELECTED) as EnginePower)
                    ) { index ->
                        result.putSerializable(ARGUMENT_VALUE_SELECTED, items[index])
                    }
                }
                viewModel.getEnginePower()
            }
            TaxInput.Emission -> {
                viewModel.emissions.observe(viewLifecycleOwner) { items ->
                    showList(
                        R.string.emission_title,
                        items.map { Pair(it.stringId, true) },
                        items.indexOf(bundle.getSerializable(ARGUMENT_VALUE_SELECTED) as Emissions)
                    ) { index ->
                        result.putSerializable(ARGUMENT_VALUE_SELECTED, items[index])
                    }
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

    private fun showList(
        @StringRes title: Int,
        items: List<Pair<Int, Boolean>>,
        selected: Int,
        onSelect: (index: Int) -> Unit
    ) {
        _binding.txtTitle.text = getString(R.string.selection_title, getString(title))

        val inflater = LayoutInflater.from(context)

        items.forEachIndexed { index, item ->
            val chip = inflater.inflate(
                R.layout.chip_list_item,
                _binding.listItems,
                false
            ) as Chip

            chip.text = getString(item.first)
            chip.isEnabled = item.second
            chip.isChecked = selected == index
            chip.setOnCheckedChangeListener { compoundButton, _ ->
                if (compoundButton.isChecked) {
                    onSelect.invoke(index)
                }
            }

            _binding.listItems.addView(chip)
        }
    }

    companion object {
        const val ARGUMENT_VALUE_TYPE = "ARGUMENT_VALUE_TYPE"
        const val ARGUMENT_VALUE_SELECTED = "ARGUMENT_VALUE_SELECTED"
    }
}