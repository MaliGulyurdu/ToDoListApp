package com.example.todolist_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist_app.data.TaskDatabase
import com.example.todolist_app.model.Task
import com.example.todolist_app.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    init{
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }
}

