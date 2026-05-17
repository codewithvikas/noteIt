package com.example.noteit

import androidx.navigation.NavHostController
import com.example.noteit.TodoDestinationArgs.TITLE_ARG
import com.example.noteit.TodoScreens.ADD_EDIT_TASK_SCREEN
import com.example.noteit.TodoScreens.TASKS_SCREEN

private object TodoScreens {
    const val TASKS_SCREEN = "tasks"
    const val ADD_EDIT_TASK_SCREEN = "addEditTask"
}

object TodoDestinations {
    const val TASKS_ROUTE = TASKS_SCREEN
    const val ADD_EDIT_TASK_ROUTE = "$ADD_EDIT_TASK_SCREEN/{$TITLE_ARG}"
}

object TodoDestinationArgs {
    const val TASK_ID_ARG = "taskId"
    const val TITLE_ARG = "title"
}

class TodoNavigationActions(private val navController: NavHostController) {

    fun navigateToAddEditTask(title: Int) {
        navController.navigate(
            route = "$ADD_EDIT_TASK_SCREEN/$title"
        )
    }
}