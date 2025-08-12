package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.model.Task
import com.example.todoapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val getAllTodo: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDao = TodoDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        getAllTodo = repository.getAllTodo
    }

    fun getTasks(): LiveData<List<Task>> = getAllTodo

    fun addTask(task: Task) {
        if (task.title.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addTask(task)
            }
        }
    }

    fun remove(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeTask(task)
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleTaskCompletion(task)
        }
    }
}