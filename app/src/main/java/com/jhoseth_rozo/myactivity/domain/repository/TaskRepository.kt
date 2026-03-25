package com.jhoseth_rozo.myactivity.domain.repository

import com.jhoseth_rozo.myactivity.domain.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun addTask(title: String)
}
