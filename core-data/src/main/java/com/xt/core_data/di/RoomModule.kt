package com.xt.core_data.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.xt.core_data.room.database.AppDatabase
import com.xt.core_data.room.database.AppRoomDB
import dagger.Binds
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
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppRoomDB =
        Room.databaseBuilder(context, AppRoomDB::class.java, "APP_DB")
            .fallbackToDestructiveMigration().apply {
                if (Debug.isDebuggerConnected()) allowMainThreadQueries()
            }.build()
}

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideImageDao(db: AppDatabase) = db.imageDao()

}

@Module
@InstallIn(SingletonComponent::class)
interface RoomBinds {

    @Binds
    @Singleton
    fun bindAppDatabase(db: AppRoomDB): AppDatabase

}
