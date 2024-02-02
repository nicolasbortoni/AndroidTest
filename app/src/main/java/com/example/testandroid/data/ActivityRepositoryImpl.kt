package com.example.testandroid.data

import com.example.testandroid.data.network.ActivityApiService
import com.example.testandroid.domain.ActivityRepository
import com.example.testandroid.domain.models.ActivityModel
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityApiService: ActivityApiService
    ): ActivityRepository {

    override suspend fun getActivity(): ActivityModel? {
        return try{
            val result = activityApiService.getActivity()
            result.toDomain()
        } catch (e: Exception){
            null
        }
    }

}