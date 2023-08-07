package cg.viciousconcepts.taxesautomobiles.ui.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.FragmentAboutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AboutFragment: BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetPopupTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.apply {
            txtTaxWalloon.movementMethod = LinkMovementMethod.getInstance()
            txtPrivacy.movementMethod = LinkMovementMethod.getInstance()

            btnDone.setOnClickListener {
                dismiss()
            }
        }
    }
}