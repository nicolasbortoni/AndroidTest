package com.example.testandroid.data

import com.example.testandroid.data.room.EventDao
import com.example.testandroid.data.room.EventEntity
import com.example.testandroid.domain.EventRepository
import com.example.testandroid.domain.models.EventModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val eventDao: EventDao): EventRepository {
    override suspend fun getEvents(): Flow<List<EventEntity>> {
        return eventDao.getEvents()
    }

    override suspend fun addEvent(event: EventModel) {
        eventDao.addEvent(event.toRoomEntity())
    }
}