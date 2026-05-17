package com.example.noteit.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteit.data.Task
import com.example.noteit.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class TaskUiState(
    val items: List<Task> = emptyList(),
    val isLoading: Boolean = false
)

class TasksViewModel(repository: TaskRepository) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = repository.getTasksStream().map { tasks ->
        TaskUiState(items = tasks, isLoading = false)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState()
    )
}

class TasksViewModelFactory(val taskRepository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(taskRepository) as T
    }
}