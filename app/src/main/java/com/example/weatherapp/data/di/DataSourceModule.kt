package com.example.weatherapp.data.di

import com.example.weatherapp.data.RemoteDataSourceIml
import com.example.weatherapp.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule  {

    @Binds
    @Singleton
    fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceIml): RemoteDataSource
}