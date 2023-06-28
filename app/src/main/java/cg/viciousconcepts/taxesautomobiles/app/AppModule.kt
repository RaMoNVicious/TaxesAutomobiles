package cg.viciousconcepts.taxesautomobiles.app

import cg.viciousconcepts.taxesautomobiles.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
}