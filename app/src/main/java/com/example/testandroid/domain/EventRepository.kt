package com.example.testandroid.domain

import com.example.testandroid.data.room.EventEntity
import com.example.testandroid.domain.models.EventModel
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvents(): Flow<List<EventEntity>>
    suspend fun addEvent(event: EventModel)
}