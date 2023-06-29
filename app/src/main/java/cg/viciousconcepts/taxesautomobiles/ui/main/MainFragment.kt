package cg.viciousconcepts.taxesautomobiles.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cg.viciousconcepts.taxesautomobiles.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        _binding.apply {
            lifecycleOwner = this@MainFragment

            viewModel.taxRegistration.observe(viewLifecycleOwner) {
                txtRegistrationTaxes.text = "%d €".format(it.toInt())
            }

            viewModel.taxAnnual.observe(viewLifecycleOwner) {
                txtAnnualTaxes.text = "%d €".format(it.toInt())
            }

            viewModel.taxInput.observe(viewLifecycleOwner) {
                btnRegion.text = it.region.toString()
                btnVehicleType.text = it.vehicleType.toString()
                btnEngineType.text = it.engineType.toString()
            }

            viewModel.getTaxes()
        }
    }
}