package com.xt.core_data.di

import com.xt.core_data.repository.ImageRepositoryImpl
import com.xt.core_domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindImageRepository(impl: ImageRepositoryImpl): ImageRepository
}
