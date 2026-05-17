package com.example.noteit.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/*
Data Access Object for the task table
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun observeAll(): Flow<List<LocalTask>>

    @Upsert
    fun upsert(toLocal: LocalTask)
}