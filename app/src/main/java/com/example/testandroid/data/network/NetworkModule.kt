package com.example.testandroid.data.network

import com.example.testandroid.data.ActivityRepositoryImpl
import com.example.testandroid.domain.ActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDateRepository(activityApiService: ActivityApiService): ActivityRepository{
        return ActivityRepositoryImpl(activityApiService)
    }

    @Provides
    fun provideDateApiService(retrofit: Retrofit): ActivityApiService {
        return retrofit.create(ActivityApiService::class.java)
    }

}