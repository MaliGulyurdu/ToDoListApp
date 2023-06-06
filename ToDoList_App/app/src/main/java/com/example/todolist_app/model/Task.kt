package com.example.todolist_app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "task_data")
// @Entity -> Assign name of the database table.
data class Task(

    @PrimaryKey(autoGenerate = true) // -> For unique "queue" value. Like use id numbers.
    val queue: Int,
    val priority: String,
    val title: String,
    val subject: String,
    var isCompleted: Boolean
): Parcelable





