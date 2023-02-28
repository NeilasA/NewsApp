package com.example.api

import com.example.api.api.NewsApi
import com.example.api.services.ApiService
import com.example.api.utils.Utils.Companion.BASE_URL
import com.example.usecase.api.NewsListApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    abstract fun bindNewsApiService(service: ApiService): NewsListApi

    companion object {

        @Provides
        fun provideNewsApi(retrofit: Retrofit): NewsApi {
            return retrofit.create(NewsApi::class.java)
        }

        @Provides
        fun provideGson(): Gson {
            return GsonBuilder().setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        }

        @Singleton
        @Provides
        fun provideNewsRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}