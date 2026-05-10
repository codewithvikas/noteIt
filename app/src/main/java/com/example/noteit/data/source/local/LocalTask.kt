package com.example.noteit.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
Internal model used to represent a task stored locally in a Room database.
This is used inside the data layer only.
 */
@Entity(
    tableName = "task"
)
data class LocalTask(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
)
