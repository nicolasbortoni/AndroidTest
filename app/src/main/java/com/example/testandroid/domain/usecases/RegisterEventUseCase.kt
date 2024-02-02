package com.example.testandroid.domain.usecases

import com.example.testandroid.domain.EventRepository
import com.example.testandroid.domain.models.EventModel
import javax.inject.Inject

class RegisterEventUseCase @Inject constructor(private val eventRepository: EventRepository) {
    suspend operator fun invoke(event: EventModel) = eventRepository.addEvent(event)
}