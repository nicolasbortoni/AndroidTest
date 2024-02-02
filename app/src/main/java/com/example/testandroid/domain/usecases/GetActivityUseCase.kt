package com.example.testandroid.domain.usecases

import com.example.testandroid.domain.ActivityRepository
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(private val activityRepository: ActivityRepository){
    suspend operator fun invoke() = activityRepository.getActivity()
}