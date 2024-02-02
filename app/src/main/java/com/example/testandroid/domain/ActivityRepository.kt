package com.example.testandroid.domain

import com.example.testandroid.domain.models.ActivityModel

interface ActivityRepository {
    suspend fun getActivity(): ActivityModel?
}