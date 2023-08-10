package cg.viciousconcepts.taxesautomobiles.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.FragmentMainBinding
import cg.viciousconcepts.taxesautomobiles.models.domain.Emission
import cg.viciousconcepts.taxesautomobiles.models.domain.EnginePower
import cg.viciousconcepts.taxesautomobiles.models.domain.EngineType
import cg.viciousconcepts.taxesautomobiles.models.domain.Region
import cg.viciousconcepts.taxesautomobiles.models.domain.TaxInput
import cg.viciousconcepts.taxesautomobiles.models.domain.VehicleType
import cg.viciousconcepts.taxesautomobiles.ui.selection.SelectionFragment
import cg.viciousconcepts.taxesautomobiles.ui.tune.TuneFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class MainFragment : Fragment() {
    private lateinit var _binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            MobileAds.initialize(it) { }
        }

        _binding.apply {
            lifecycleOwner = this@MainFragment

            adView.loadAd(AdRequest.Builder().build())

            btnAbout.setOnClickListener {
                activity
                    ?.findNavController(R.id.nav_host_fragment_container)
                    ?.navigate(R.id.action_mainFragment_to_aboutFragment)
            }

            viewModel.taxInput.observe(viewLifecycleOwner) { tax ->
                btnRegion.text = getString(tax.region.stringId)
                btnVehicleType.text = getString(tax.vehicleType.stringId)
                btnEngineType.text = getString(tax.engineType.stringId)
                btnAge.text = when {
                    tax.age < 1 -> getString(R.string.age_value_lower)
                    tax.age >= 30 -> getString(R.string.age_value_greater, tax.age)
                    else -> getString(R.string.age_value, tax.age)
                }
                btnEnginePower.text = getString(
                    R.string.engine_power_value,
                    getString(tax.enginePower.stringId)
                )
                btnEngineSize.text = when {
                    tax.engineSize <= 500 -> getString(
                        R.string.engine_size_value_lower,
                        tax.engineSize
                    )

                    tax.engineSize >= 7500 -> getString(
                        R.string.engine_size_value_greater,
                        tax.engineSize
                    )

                    else -> getString(R.string.engine_size_value, tax.engineSize)
                }
                btnEmissions.text = getString(
                    R.string.emission_value,
                    getString(
                        R.string.emission_from_to,
                        tax.emission.from,
                        tax.emission.to
                    )
                )
                btnChildren.text = when {
                    tax.children == 0 -> getString(R.string.children_value_lower)
                    tax.children >= 4 -> getString(R.string.children_value_greater, tax.children)
                    else -> getString(R.string.children_value, tax.children)
                }

                setFragmentResultListener(VALUE_FOR_RESULT, ::onResult)

                btnRegion.setOnClickListener {
                    showSelection(TaxInput.Region, tax.region)
                }

                btnVehicleType.setOnClickListener {
                    showSelection(TaxInput.VehicleType, tax.vehicleType)
                }

                btnEngineType.setOnClickListener {
                    showSelection(TaxInput.EngineType, tax.engineType)
                }

                btnAge.setOnClickListener {
                    showTune(TaxInput.Age, tax.age)
                }

                btnEnginePower.setOnClickListener {
                    showSelection(TaxInput.EnginePower, tax.enginePower)
                }

                btnEngineSize.setOnClickListener {
                    showTune(TaxInput.EngineSize, tax.engineSize)
                }

                btnEmissions.setOnClickListener {
                    showSelection(TaxInput.Emission, tax.emission)
                }

                btnChildren.setOnClickListener {
                    showTune(TaxInput.Children, tax.children)
                }

                viewModel.getTaxAnnual()
                viewModel.getTaxRegistration()
            }

            viewModel.taxAnnual.observe(viewLifecycleOwner) {
                txtAnnualTaxes.text = getString(R.string.tax_value, it)
            }

            viewModel.taxRegistration.observe(viewLifecycleOwner) {
                txtRegistrationTaxes.text = getString(R.string.tax_value, it)
            }

        }
    }

    private fun onResult(requestKey: String, bundle: Bundle) {
        bundle.takeIf {
            requestKey == VALUE_FOR_RESULT
                    && it.containsKey(SelectionFragment.ARGUMENT_VALUE_TYPE)
                    && it.containsKey(SelectionFragment.ARGUMENT_VALUE_SELECTED)
        }?.let {
            when (bundle.getSerializable(SelectionFragment.ARGUMENT_VALUE_TYPE) as TaxInput) {
                TaxInput.Region -> {
                    (bundle.getSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED) as Region?)
                        ?.let { viewModel.updateTax(it) }
                }
                TaxInput.VehicleType -> {
                    (bundle.getSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED) as VehicleType?)
                        ?.let { viewModel.updateTax(it) }
                }
                TaxInput.EngineType -> {
                    (bundle.getSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED) as EngineType?)
                        ?.let { viewModel.updateTax(it) }
                }
                TaxInput.Age -> {
                    (bundle.getInt(SelectionFragment.ARGUMENT_VALUE_SELECTED))
                        .let { viewModel.updateAge(it) }
                }
                TaxInput.EnginePower -> {
                    (bundle.getSerializable(SelectionFragment.ARGUMENT_VALUE_SELECTED) as EnginePower?)
                        ?.let { viewModel.updateTax(it) }
                }
                TaxInput.EngineSize -> {
                    (bundle.getInt(TuneFragment.ARGUMENT_VALUE_SELECTED))
                        .let { viewModel.updateEngineSize(it) }
                }
                TaxInput.Emission -> {
                    (bundle.getSerializable(TuneFragment.ARGUMENT_VALUE_SELECTED) as Emission?)
                        ?.let { viewModel.updateTax(it) }
                }
                TaxInput.Children -> {
                    (bundle.getInt(SelectionFragment.ARGUMENT_VALUE_SELECTED))
                        .let { viewModel.updateChildren(it) }
                }
            }
        }
    }

    private fun showSelection(type: Serializable, value: Serializable) {
        activity
            ?.findNavController(R.id.nav_host_fragment_container)
            ?.navigate(
                R.id.action_mainFragment_to_selectionFragment,
                bundleOf(
                    SelectionFragment.ARGUMENT_VALUE_TYPE to type,
                    SelectionFragment.ARGUMENT_VALUE_SELECTED to value
                )
            )
    }

    private fun showTune(type: Serializable, value: Int) {
        activity
            ?.findNavController(R.id.nav_host_fragment_container)
            ?.navigate(
                R.id.action_mainFragment_to_tuneFragment,
                bundleOf(
                    TuneFragment.ARGUMENT_VALUE_TYPE to type,
                    TuneFragment.ARGUMENT_VALUE_SELECTED to value
                )
            )
    }

    companion object {
        const val VALUE_FOR_RESULT = "VALUE_FOR_RESULT"
    }
}