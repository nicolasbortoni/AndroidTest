package com.example.testandroid.domain.usecases

import com.example.testandroid.data.room.EventEntity
import com.example.testandroid.domain.EventRepository
import com.example.testandroid.domain.models.EventModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val eventRepository: EventRepository) {
    suspend operator fun invoke(): Flow<List<EventEntity>> = eventRepository.getEvents()
}