package com.xt.core_data.room.enity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ImageEntity.TABLE_IMAGE)
data class ImageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val isFavorited: Boolean = false,
) {
    companion object {
        const val TABLE_IMAGE = "table_image"
    }
}
