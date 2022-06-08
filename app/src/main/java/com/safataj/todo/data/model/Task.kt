package com.safataj.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String,
    @ColumnInfo(name = "is_done") var isDone: Boolean = false,
    var timestamp: Long,
)
