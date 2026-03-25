package com.jhoseth_rozo.myactivity.presentation

import com.jhoseth_rozo.myactivity.domain.model.Task
import com.jhoseth_rozo.myactivity.domain.repository.TaskRepository
import com.jhoseth_rozo.myactivity.domain.usecase.GetPendingTasksUseCase
import com.jhoseth_rozo.myactivity.presentation.viewmodel.TaskViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TaskViewModel
    
    private class FakeRepo(val tasks: List<Task>) : TaskRepository {
        override suspend fun getAllTasks() = tasks
        override suspend fun addTask(title: String) {}
    }

    @Before
    fun setUp() {
        val repo = FakeRepo(listOf(
            Task(1, "Task 1"),
            Task(2, "Task 2", completed = true)
        ))
        val useCase = GetPendingTasksUseCase(repo)
        viewModel = TaskViewModel(useCase)
    }

    @Test
    fun `carga de tareas produce estado Success con datos filtrados`() = runTest {
        viewModel.loadTasks()
        val state = viewModel.uiState.value
        assertTrue(state is TaskUiState.Success)
        assertEquals(1, (state as TaskUiState.Success).tasks.size)
    }
}
