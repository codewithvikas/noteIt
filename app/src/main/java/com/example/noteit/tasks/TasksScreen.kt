package com.example.noteit.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TasksScreen(
    viewModel: TasksViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->

        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        TaskContent(tasks = uiState.value, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun TaskContent(tasks: TaskUiState, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        for (item in tasks.items) {
            Text(text = item.title)
        }
    }
}