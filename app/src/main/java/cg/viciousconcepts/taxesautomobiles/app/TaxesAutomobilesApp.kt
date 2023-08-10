package cg.viciousconcepts.taxesautomobiles.app

import android.app.Application
import android.util.Log
import cg.viciousconcepts.taxesautomobiles.BuildConfig
import cg.viciousconcepts.taxesautomobiles.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TaxesAutomobilesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TaxesAutomobilesApp)
            modules(appModule)
        }

        Log.d("REMOTE", "Init")
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.setConfigSettingsAsync(
            FirebaseRemoteConfigSettings
                .Builder()
                .setFetchTimeoutInSeconds(30)
                .setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 0 else 3600)
                .build()
        )

        // TODO: fetch data in loading fragment
        remoteConfig
            .fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("REMOTE", "Load Success!")
                } else {
                    Log.e("REMOTE", "Failed to load remote!")
                }
            }
    }
}