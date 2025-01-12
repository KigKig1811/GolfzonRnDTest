package com.xt.core_data.room.database

import com.xt.core_data.room.dao.ImageDao
import com.xt.core_data.room.dao.RemoteKeyDao


interface AppDatabase {
    fun imageDao(): ImageDao
    fun remoteKeyBao(): RemoteKeyDao
}
