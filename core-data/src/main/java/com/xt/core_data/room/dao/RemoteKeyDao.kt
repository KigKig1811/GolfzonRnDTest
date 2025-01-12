package com.xt.core_data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xt.core_data.room.enity.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE `query` = :query")
    suspend fun remoteKey(query: String): RemoteKey

    @Query("DELETE FROM remote_keys WHERE `query` = :query")
    suspend fun deleteKey(query: String)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}