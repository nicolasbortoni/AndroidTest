package com.example.testandroid.data.room

import android.content.Context
import androidx.room.Room
import com.example.testandroid.data.EventRepositoryImpl
import com.example.testandroid.domain.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideEventDao(eventDatabase: EventDatabase): EventDao{
        return eventDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun provideEventDatabase(@ApplicationContext appContext: Context): EventDatabase {
        return Room
            .databaseBuilder(
            appContext,
            EventDatabase::class.java,
            "EventDatabase"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideEventRepository(eventDao: EventDao): EventRepository {
        return EventRepositoryImpl(eventDao)
    }

}