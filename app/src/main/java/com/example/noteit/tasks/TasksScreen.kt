package com.example.noteit.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteit.R

@Composable
fun TasksScreen(
    onAddTask: () -> Unit,
    viewModel: TasksViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            LargeFloatingActionButton(onClick = onAddTask) {
                Icon(
                    painter = painterResource(R.drawable.task_add_24),
                    contentDescription = stringResource(id = R.string.new_task)
                )
            }
        }
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