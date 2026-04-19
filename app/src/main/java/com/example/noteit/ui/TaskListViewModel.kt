package com.example.noteit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteit.data.TaskListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class TaskListViewModel(val taskListRepository: TaskListRepository) : ViewModel() {
    val tasks = taskListRepository.tasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(500),
        initialValue = emptyList()
    )
}

class TaskListViewModelFactory(val taskListRepository: TaskListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel(taskListRepository) as T
    }
}