package com.example.testandroid.domain.models

import com.example.testandroid.data.room.EventEntity

data class EventModel(
    var eventType: String,
    var eventDate: String
){

    fun toRoomEntity(): EventEntity {
        return EventEntity(eventType = eventType, eventDate = eventDate)
    }

}