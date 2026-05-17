package com.example.noteit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.noteit.data.DefaultTaskRepository
import com.example.noteit.tasks.TasksViewModel
import com.example.noteit.tasks.TasksViewModelFactory
import com.example.noteit.ui.theme.NoteItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModelStoreOwner = LocalLifecycleOwner.current as ViewModelStoreOwner
            val todoApplication = (application as TodoApplication)
            val taskDao = todoApplication.taskDao
            val viewModel = ViewModelProvider(
                owner = viewModelStoreOwner,
                factory = TasksViewModelFactory(
                    DefaultTaskRepository(
                        localDataSource = taskDao,
                        dispatcher = todoApplication.ioDispatcher
                    )
                )
            ).get("ViewModelKey", TasksViewModel::class.java)

            NoteItTheme {
                TodoNavGraph(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteItTheme {
        Greeting("Android")
    }
}