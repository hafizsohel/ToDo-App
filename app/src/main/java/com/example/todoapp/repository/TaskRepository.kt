package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.dao.TaskDao
import com.example.todoapp.model.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val getAllTodo: LiveData<List<Task>> = taskDao.getAllTodo()

     suspend fun addTask(task: Task) {
        taskDao.addTodo(task)
    }

     suspend fun removeTask(task: Task) {
        taskDao.deleteTodo(task.id)
    }

     suspend fun toggleTaskCompletion(task: Task) {
         taskDao.toggleTodoCompletion(task.id)
    }
}