package com.example.suitmedia.di

import com.example.suitmedia.data.UserRepository
import com.example.suitmedia.data.api.ApiConfig

object Injection {

    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}