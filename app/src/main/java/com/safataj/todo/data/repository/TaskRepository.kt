package com.safataj.todo.data.repository

import com.safataj.todo.data.local.dao.TaskDao
import com.safataj.todo.data.model.Task
import com.safataj.todo.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    val tasks: Flow<List<Task>> get() = dao.getAll()
    val doneTasks: Flow<List<Task>> get() = dao.getDone()
    val undoneTasks: Flow<List<Task>> get() = dao.getUndone()

    suspend fun addTask(task: Task) {
        return withContext(ioDispatcher) {
            dao.insert(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        return withContext(ioDispatcher) {
            dao.delete(task)
        }
    }

    suspend fun editTask(task: Task) {
        return withContext(ioDispatcher) {
            dao.update(task)
        }
    }
}