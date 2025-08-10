package com.example.todoapp.repository

import com.example.todoapp.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TaskRepository @Inject constructor() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

     fun addTask(task: Task) {
        _tasks.value += task
    }

     fun removeTask(task: Task) {
        _tasks.value -= task
    }

     fun toggleTaskCompletion(task: Task) {
        _tasks.value = _tasks.value.map { currentTask ->
            if (currentTask.id == task.id) {
                currentTask.copy(isCompleted = !currentTask.isCompleted)
            } else {
                currentTask
            }
        }
    }
}