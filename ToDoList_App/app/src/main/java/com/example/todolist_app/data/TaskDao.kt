package com.example.todolist_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist_app.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // onConflict = Çakışma durumunda ...
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM task_data ORDER BY isCompleted ASC, queue ASC") // As you can see, data's sorting by 'queue' values. Ranking from small to large.
    // * -> Return all information of task_data
    // If you write something like 'name' instead of '*' that will just return names.


    fun readAllData(): LiveData<List<Task>>
}


