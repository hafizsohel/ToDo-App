package com.example.todoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.model.Task

@Dao
interface TaskDao{

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodo(): LiveData<List<Task>>

    @Insert
    fun addTodo(task: Task)

    @Query("DELETE FROM todo_table WHERE id= :id")
    fun deleteTodo(id: Int)

    @Query("UPDATE todo_table SET isCompleted= NOT isCompleted WHERE id=:id")
    fun toggleTodoCompletion(id: Int)
}