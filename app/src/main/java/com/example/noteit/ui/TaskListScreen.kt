package com.example.noteit.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteit.data.Task
import com.example.noteit.data.TaskListRepository
import com.example.noteit.ui.theme.NoteItTheme

@Composable
fun TaskListScreen() {
    val viewModelStoreOwner = LocalLifecycleOwner.current as ViewModelStoreOwner
    Log.d("ViewModelStoreOwner:", "${viewModelStoreOwner.viewModelStore.keys()}")
    viewModelStoreOwner.viewModelStore
    val viewModel = ViewModelProvider(
        owner = viewModelStoreOwner,
        factory = TaskListViewModelFactory(TaskListRepository())
    ).get("${Math.random().times(100)}", TaskListViewModel::class.java)
    Log.d("viewModel", "$viewModel")
    val tasks = viewModel.tasks.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        Button(onClick = {
            viewModel.addTask(Task("New Task", "New Description"))
        }) {
            Text(text = "Add Task")
        }
    }) { innerPadding ->
        TaskList(tasks = tasks.value, modifier = Modifier.padding(innerPadding))
    }

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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = task.title,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
            )
            Text(text = task.description, style = TextStyle(fontSize = 12.sp))
        }
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