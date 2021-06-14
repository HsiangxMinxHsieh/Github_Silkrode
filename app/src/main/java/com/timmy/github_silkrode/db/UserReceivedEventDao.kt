package com.timmy.github_silkrode.db

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserReceivedEventDao {

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(receivedEvents: List<ReceivedEvent>)

    @WorkerThread
    @Query("SELECT * FROM user_received_events ORDER BY id ASC")
    fun queryEvents(): PagingSource<Int, ReceivedEvent>

    @WorkerThread
    @Query("DELETE FROM user_received_events")
    suspend fun clearReceivedEvents()

    @WorkerThread
    @Query("SELECT MAX(id) + 1 FROM user_received_events")
    suspend fun getNextIndexInReceivedEvents(): Int?
}