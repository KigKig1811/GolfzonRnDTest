package com.xt.core_data.room.database

import com.xt.core_data.room.dao.ImageDao


interface AppDatabase {
    fun imageDao(): ImageDao
}
