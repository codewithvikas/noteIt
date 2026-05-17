package com.example.noteit.tasks

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteit.R
import com.example.noteit.data.Task

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

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        TaskContent(
            loading = uiState.isLoading,
            tasks = uiState.items,
            noTasksLabel = uiState.filteringUiInfo.noTasksLabel,
            notTasksIconRes = uiState.filteringUiInfo.noTasksIconRes,
            onRefresh = viewModel::refresh,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun TaskContent(
    loading: Boolean,
    tasks: List<Task>,
    @StringRes noTasksLabel: Int,
    @DrawableRes notTasksIconRes: Int,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    LoadingContent(
        loading = loading,
        onRefresh = onRefresh,
        empty = tasks.isEmpty() && !loading,
        emptyContent = {
            TaskEmptyContent(
                noTasksLabel = noTasksLabel,
                noTaskIconRes = notTasksIconRes,
                modifier = modifier
            )
        }
    ) {
        Column(modifier = modifier) {
            for (item in tasks) {
                Text(text = item.title)
            }
        }
    }
}

@Composable
fun TaskEmptyContent(
    @StringRes noTasksLabel: Int,
    @DrawableRes noTaskIconRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(noTaskIconRes),
            contentDescription = stringResource(R.string.no_tasks_image),
            modifier = Modifier.size(96.dp)
        )
        Text(text = stringResource(id = noTasksLabel))
    }
}

@Composable
fun LoadingContent(
    loading: Boolean,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        PullToRefreshBox(
            isRefreshing = loading,
            onRefresh = onRefresh,
            modifier = modifier,
            content = content,
        )
    }
}