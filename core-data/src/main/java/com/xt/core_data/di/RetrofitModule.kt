package com.xt.core_data.di

import androidx.multidex.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.xt.core_data.remote.ImageService
import com.xt.core_data.remote.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        if (BuildConfig.DEBUG) return null
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideHttpEventListener(): LoggingEventListener.Factory? {
        if (!BuildConfig.DEBUG) return null
        return LoggingEventListener.Factory()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor?,
        loggingEventListener: LoggingEventListener.Factory?,
        networkInterceptor: NetworkInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().followRedirects(true).apply {
        addInterceptor(networkInterceptor)
        if (null != httpLoggingInterceptor) {
            addInterceptor(httpLoggingInterceptor)
        }
        if (null != loggingEventListener) {
            eventListenerFactory(loggingEventListener)
        }
    }.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).hostnameVerifier { _, _ -> true }.build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder().apply {
    }.baseUrl("https://api.thedogapi.com/")
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideHttpConverter(gson: Gson): GsonConverterFactory {
        val gsonConverter = GsonConverterFactory.create(gson)
        return gsonConverter
    }

    @Singleton
    @Provides
    fun provideImageService(retrofit: Retrofit): ImageService = retrofit.create()

}
