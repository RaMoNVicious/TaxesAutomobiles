package cg.viciousconcepts.taxesautomobiles.repositories

import cg.viciousconcepts.taxesautomobiles.models.domain.EngineSize
import cg.viciousconcepts.taxesautomobiles.models.domain.EngineSizes
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow

class EngineSizeRepository {

    fun getData() =
        flow {
            emit(
                Gson().fromJson<EngineSizes>(
                    Firebase.remoteConfig.getString(REMOTE_KEY_SIZES),
                    object : TypeToken<EngineSizes>() {}.type
                ).let {
                    (it.min..it.max step it.step).toList()
                }
            )
        }


    fun getCV(size: Int) =
        flow {
            emit(
                (sizeToCv()?.firstOrNull { size in it.from..it.to } ?: EngineSize()).cv
            )
        }

    private fun sizeToCv() =
        Gson().fromJson<List<EngineSize>>(
            Firebase.remoteConfig.getString(REMOTE_KEY_SIZE_2_CV),
            object : TypeToken<List<EngineSize>>() {}.type
        )

    companion object {
        const val REMOTE_KEY_SIZE_2_CV = "size2cv"
        const val REMOTE_KEY_SIZES = "engine_size"
    }

}