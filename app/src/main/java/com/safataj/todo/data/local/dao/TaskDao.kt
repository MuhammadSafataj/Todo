package com.safataj.todo.data.local.dao

import androidx.room.*
import com.safataj.todo.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE is_done")
    fun getDone(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE NOT is_done")
    fun getUndone(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}