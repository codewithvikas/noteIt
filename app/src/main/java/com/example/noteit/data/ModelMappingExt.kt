package com.example.noteit.data

import com.example.noteit.data.source.local.LocalTask

fun LocalTask.toExternal() =
    Task(
        id = id,
        title = title,
        description = description
    )

@JvmName("localToExternal")
fun List<LocalTask>.toExternal() = map(LocalTask::toExternal)

fun Task.toLocal() = LocalTask(id = id, title = title, description = description)