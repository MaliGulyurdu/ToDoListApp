package com.example.todolist_app.repository

import androidx.lifecycle.LiveData
import com.example.todolist_app.data.TaskDao
import com.example.todolist_app.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }
}