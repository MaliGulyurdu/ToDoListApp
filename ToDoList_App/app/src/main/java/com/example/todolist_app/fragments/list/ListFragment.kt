package com.example.todolist_app.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist_app.R
import com.example.todolist_app.databinding.FragmentListBinding
import com.example.todolist_app.viewmodel.TaskViewModel

class ListFragment : Fragment() {
    private lateinit var taskViewModel: TaskViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Recyclerview
        val adapter = ListAdapter(taskViewModel)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.readAllData.observe(viewLifecycleOwner, Observer {task ->
            adapter.setData(task)
        })

        return binding.root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}