package cg.viciousconcepts.taxesautomobiles.app

import cg.viciousconcepts.taxesautomobiles.repositories.AgeRepository
import cg.viciousconcepts.taxesautomobiles.repositories.ChildrenRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EmissionRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EnginePowerRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EngineSizeRepository
import cg.viciousconcepts.taxesautomobiles.repositories.EngineTypeRepository
import cg.viciousconcepts.taxesautomobiles.repositories.RegionRepository
import cg.viciousconcepts.taxesautomobiles.repositories.RegistrationRepository
import cg.viciousconcepts.taxesautomobiles.repositories.TaxUseCase
import cg.viciousconcepts.taxesautomobiles.repositories.VehicleTypeRepository
import cg.viciousconcepts.taxesautomobiles.ui.main.MainViewModel
import cg.viciousconcepts.taxesautomobiles.ui.selection.SelectionViewModel
import cg.viciousconcepts.taxesautomobiles.ui.tune.TuneViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { SelectionViewModel(get(), get(), get(), get(), get()) }
    viewModel { TuneViewModel(get(), get(), get()) }

    single { RegionRepository() }
    single { VehicleTypeRepository() }
    single { EngineTypeRepository() }
    single { AgeRepository() }
    single { EnginePowerRepository() }
    single { EngineSizeRepository() }
    single { EmissionRepository() }
    single { ChildrenRepository() }
    single { RegistrationRepository() }

    single {
        androidApplication()
            .getSharedPreferences(
                "default",
                android.content.Context.MODE_PRIVATE
            )
    }

    factory { TaxUseCase(get(), get(), get()) }
}