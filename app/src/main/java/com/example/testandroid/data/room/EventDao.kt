package com.example.testandroid.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert
    suspend fun addEvent(event: EventEntity)

    @Query("SELECT * FROM EventEntity")
    fun getEvents(): Flow<List<EventEntity>>

}