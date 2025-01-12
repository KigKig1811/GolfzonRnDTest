package com.xt.core_data.room.enity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val imageId: String,
    val nextKey: Int?
)
