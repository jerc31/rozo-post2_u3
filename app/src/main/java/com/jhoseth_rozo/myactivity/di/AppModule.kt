package com.jhoseth_rozo.myactivity.di

import com.jhoseth_rozo.myactivity.data.repository.InMemoryTaskRepository
import com.jhoseth_rozo.myactivity.domain.repository.TaskRepository
import com.jhoseth_rozo.myactivity.domain.usecase.GetPendingTasksUseCase
import com.jhoseth_rozo.myactivity.presentation.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositorio: instancia única en toda la aplicación
    single<TaskRepository> { InMemoryTaskRepository() }
    
    // Use Case: nueva instancia por solicitud (sin estado)
    factory { GetPendingTasksUseCase(get()) } // get() inyecta TaskRepository
    
    // ViewModel: scoped al ciclo de vida del ViewModel
    viewModel { TaskViewModel(get()) } // get() inyecta GetPendingTasksUseCase
}
