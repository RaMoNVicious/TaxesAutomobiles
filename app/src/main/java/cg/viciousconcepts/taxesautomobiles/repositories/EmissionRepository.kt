package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.Emission
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow

class EmissionRepository {

    fun getData() =
        flow<List<Emission>> {
            emit(
                Gson().fromJson(
                    Firebase.remoteConfig.getString(REMOTE_KEY),
                    object : TypeToken<List<Emission>>() {}.type
                )
            )
        }

    companion object {
        const val REMOTE_KEY = "emissions"
    }

}