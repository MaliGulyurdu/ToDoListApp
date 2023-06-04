package com.example.todolist_app.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist_app.R
import com.example.todolist_app.model.Task
import com.example.todolist_app.viewmodel.TaskViewModel
import com.example.todolist_app.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.btnAddTask.setOnClickListener {
            insertDataToDatabase()
        }


        return binding.root
    }

    private fun insertDataToDatabase(){
        val priorityRate:String = binding.spinner.selectedItem.toString()
        val subject:String = binding.subjectEditText.text.toString()
        val title:String = binding.titleEditText.text.toString()
        if(inputCheck(subject,title,priorityRate)) {
            // Task create
            val task = Task(0,priorityRate,title,subject,false)
            taskViewModel.addTask(task)

            // Toast message -> Success
            Toast.makeText(requireContext(), "Created a task.",Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

            else {
                Toast.makeText(requireContext(), "Please fill out all fields.",Toast.LENGTH_LONG).show()
            }

    // Log.d("inputs","Rate: $priorityRate Title: $title Subject: $subject Status: Open")

    }

    private fun inputCheck(subject:String, title: String, priorityRate: String): Boolean{
        return !(TextUtils.isEmpty(subject) || TextUtils.isEmpty(title) || TextUtils.isEmpty(priorityRate))
    }



}