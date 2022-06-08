package com.safataj.todo.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.safataj.todo.data.model.Task
import com.safataj.todo.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    val tasks: LiveData<List<Task>> = repository.tasks.asLiveData()
    val doneTasks: LiveData<List<Task>> = repository.doneTasks.asLiveData()
    val undoneTasks: LiveData<List<Task>> = repository.undoneTasks.asLiveData()

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun editTask(task: Task) {
        viewModelScope.launch {
            repository.editTask(task)
        }
    }
}