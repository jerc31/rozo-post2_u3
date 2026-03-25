package com.jhoseth_rozo.myactivity.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhoseth_rozo.myactivity.domain.usecase.GetPendingTasksUseCase
import com.jhoseth_rozo.myactivity.presentation.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val getPendingTasks: GetPendingTasksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskUiState>(TaskUiState.Loading)
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TaskUiState.Loading
            _uiState.value = try {
                TaskUiState.Success(getPendingTasks())
            } catch (e: Exception) {
                TaskUiState.Error(e.message ?: "Error")
            }
        }
    }
}
