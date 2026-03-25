package com.jhoseth_rozo.myactivity.presentation

import com.jhoseth_rozo.myactivity.domain.model.Task

sealed class TaskUiState {
    object Loading : TaskUiState()
    data class Success(val tasks: List<Task>) : TaskUiState()
    data class Error(val message: String) : TaskUiState()
}
