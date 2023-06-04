package com.example.todolist_app.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist_app.R
import com.example.todolist_app.databinding.FragmentUpdateBinding
import com.example.todolist_app.fragments.list.ListAdapter
import com.example.todolist_app.model.Task
import com.example.todolist_app.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {
    private lateinit var taskViewModel: TaskViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Get string-array information from XML file.
        val spinnerItems = resources.getStringArray(R.array.spinner_items)

        val updatedItem = args.currentTask.priority
        val updatedItemPosition = spinnerItems.indexOf(updatedItem)

        // Show updated data on the fragment
        binding.updateTitleEditText.setText(args.currentTask.title)
        binding.updateSubjectEditText.setText(args.currentTask.subject)
        binding.updateSpinner.setSelection(updatedItemPosition)

        binding.btnUpdateTask.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem(){
        val newTitle = binding.updateTitleEditText.text.toString()
        val newSubject = binding.updateSubjectEditText.text.toString()
        val newPriority = binding.updateSpinner.selectedItem.toString()

        if (inputCheck(newSubject,newTitle,newPriority)){
            // Create "Task" object
            val updatedTask = Task(args.currentTask.queue,newPriority,newSubject,newTitle,false)

            // Update current object
            taskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Task updated successfully!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.",Toast.LENGTH_LONG).show()
        }
    }


    private fun inputCheck(subject:String, title: String, priorityRate: String): Boolean{
        return !(TextUtils.isEmpty(subject) || TextUtils.isEmpty(title) || TextUtils.isEmpty(priorityRate))
    }

}