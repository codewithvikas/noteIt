package com.example.noteit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteit.data.Task
import com.example.noteit.data.TaskListRepository
import com.example.noteit.ui.theme.NoteItTheme

@Composable
fun TaskListScreen(modifier: Modifier) {
    val viewModel = ViewModelProvider(
        owner = LocalLifecycleOwner.current as ViewModelStoreOwner,
        factory = TaskListViewModelFactory(TaskListRepository())
    ).get(TaskListViewModel::class.java)
    val tasks = viewModel.tasks.collectAsStateWithLifecycle()
    TaskList(tasks.value, modifier)

}

@Composable
fun TaskList(tasks: List<Task>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(tasks.size) { index ->
            TaskItem(tasks[index])
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Column {
        Text(text = "Title:${task.title}")
        Text(text = "Description:${task.description}")
    }
}

@Preview(showSystemUi = true)
@Composable
fun TaskListPreview() {
    val taskList = listOf(
        Task("Task 1", "Description 1"),
        Task("Task 2", "Description 2")
    )

    NoteItTheme() {
        Scaffold { innerPadding ->
            TaskList(taskList, modifier = Modifier.padding(innerPadding))
        }

    }
}