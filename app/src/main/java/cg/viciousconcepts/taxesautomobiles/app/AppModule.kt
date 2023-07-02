package cg.viciousconcepts.taxesautomobiles.app

import cg.viciousconcepts.taxesautomobiles.ui.main.MainViewModel
import cg.viciousconcepts.taxesautomobiles.ui.repositories.AgeRepository
import cg.viciousconcepts.taxesautomobiles.ui.repositories.EmissionRepository
import cg.viciousconcepts.taxesautomobiles.ui.repositories.EnginePowerRepository
import cg.viciousconcepts.taxesautomobiles.ui.repositories.EngineSizeRepository
import cg.viciousconcepts.taxesautomobiles.ui.selection.SelectionViewModel
import cg.viciousconcepts.taxesautomobiles.ui.tune.TuneViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { SelectionViewModel() }
    viewModel { TuneViewModel(get(), get(), get(), get()) }

    single { AgeRepository() }
    single { EnginePowerRepository() }
    single { EngineSizeRepository() }
    single { EmissionRepository() }
}