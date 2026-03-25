package com.jhoseth_rozo.myactivity.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhoseth_rozo.myactivity.domain.model.Task
import com.jhoseth_rozo.myactivity.presentation.TaskUiState
import com.jhoseth_rozo.myactivity.presentation.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(viewModel: TaskViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Lista de Tareas",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (val state = uiState) {
            is TaskUiState.Loading ->
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

            is TaskUiState.Success ->
                LazyColumn {
                    items(state.tasks, key = { it.id }) { task ->
                        TaskItem(task = task)
                    }
                }

            is TaskUiState.Error ->
                Text(state.message, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.completed, onCheckedChange = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = task.title,
                style = if (task.completed)
                    MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough)
                else MaterialTheme.typography.bodyMedium
            )
        }
    }
}
