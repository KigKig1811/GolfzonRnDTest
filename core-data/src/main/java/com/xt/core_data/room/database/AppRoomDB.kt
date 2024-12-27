package com.xt.core_data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xt.core_data.room.enity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class AppRoomDB : RoomDatabase(), AppDatabase
