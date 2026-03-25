package com.jhoseth_rozo.myactivity.domain.usecase

import com.jhoseth_rozo.myactivity.domain.model.Task
import com.jhoseth_rozo.myactivity.domain.repository.TaskRepository

class GetPendingTasksUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(): List<Task> {
        return repository.getAllTasks()
            .filter { !it.completed }        // Solo tareas pendientes
            .sortedByDescending { it.id }     // Más recientes primero
    }
}
