package com.example.noteit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteit.TodoDestinationArgs.TITLE_ARG
import com.example.noteit.addedittask.AddEditTaskScreen
import com.example.noteit.tasks.TasksScreen
import com.example.noteit.tasks.TasksViewModel

@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TodoDestinations.TASKS_ROUTE,
    navAction: TodoNavigationActions = remember(navController) {
        TodoNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = TodoDestinations.TASKS_ROUTE) {
            TasksScreen(
                viewModel = viewModel,
                onAddTask = {
                    navAction.navigateToAddEditTask(R.string.new_task)
                })
        }
        composable(
            route = TodoDestinations.ADD_EDIT_TASK_ROUTE,
            arguments = listOf(
                navArgument(name = TITLE_ARG) { type = NavType.IntType }
            )
        ) { entry ->
            val title = entry.arguments?.getInt(TITLE_ARG)!!
            AddEditTaskScreen(
                topBarTitle = title,
                onBack = {}
            )
        }
    }
}
