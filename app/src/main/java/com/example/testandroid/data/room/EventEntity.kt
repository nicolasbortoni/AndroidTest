package com.example.testandroid.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testandroid.domain.models.EventModel

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "event_type")
    val eventType: String = "",

    @ColumnInfo(name = "event_date")
    val eventDate: String = ""

){

    fun toDomain(): EventModel{
        return EventModel(eventType, eventDate)
    }

}