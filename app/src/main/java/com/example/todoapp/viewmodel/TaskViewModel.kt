package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.Task
import com.example.todoapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> = repository.tasks

    init {
        if (tasks.value.isEmpty()){
            repository.addTask(Task(
                title = "This is Demo",
                id = generateId(),
                isCompleted = false
            ))
            repository.addTask(Task(
                title = "This is Demo 2",
                id = generateId(),
                isCompleted = true
            ))
        }
    }

     fun addTask(title: String) {
        if (title.isNotBlank()) {
            val newTask = Task(id = System.currentTimeMillis().toInt(), title)
            viewModelScope.launch {
                repository.addTask(newTask)
            }
        }
    }

     fun remove(task: Task) {
        viewModelScope.launch {
            repository.removeTask(task)
        }
    }

     fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            repository.toggleTaskCompletion(task)
        }
    }

    private fun generateId(): Int {
        return UUID.randomUUID().toString().hashCode()
    }
}