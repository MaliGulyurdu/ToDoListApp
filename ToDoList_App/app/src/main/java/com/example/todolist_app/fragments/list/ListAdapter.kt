package com.example.todolist_app.fragments.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_app.model.Task
import com.example.todolist_app.databinding.CustomRowBinding
import com.example.todolist_app.viewmodel.TaskViewModel

class ListAdapter(private val taskViewModel: TaskViewModel): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    inner class MyViewHolder(private val binding: CustomRowBinding, private val taskViewModel: TaskViewModel):RecyclerView.ViewHolder(binding.root){
        // View Binding
        val titleTxt: TextView = binding.titleTxt
        val subjectTxt: TextView = binding.subjectTxt
        val priorityTxt: TextView = binding.priorityTxt
        val idTxt: TextView = binding.idTxt
        val rowLayout: ConstraintLayout = binding.rowLayout
        val checkBox: CheckBox = binding.checkBox

        // Checkbox update

        fun bind(task: Task){
            binding.apply {
                checkBox.isChecked = task.isCompleted
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    task.isCompleted = isChecked
                    // O anlık görünüm değişimi
                    titleTxt.paintFlags = if (task.isCompleted) Paint.STRIKE_THRU_TEXT_FLAG else 0
                    subjectTxt.paintFlags = if (task.isCompleted) Paint.STRIKE_THRU_TEXT_FLAG else 0
                    taskViewModel.updateTask(task)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // View Binding
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomRowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding,taskViewModel)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Modify according to the data (CustomRow elements)
        val task = taskList[position]
        holder.idTxt.text = task.queue.toString()
        holder.subjectTxt.text = task.subject
        holder.subjectTxt.paintFlags = if (task.isCompleted) Paint.STRIKE_THRU_TEXT_FLAG else 0
        holder.titleTxt.text = task.title
        holder.titleTxt.paintFlags = if (task.isCompleted) Paint.STRIKE_THRU_TEXT_FLAG else 0
        holder.priorityTxt.text = task.priority
        holder.checkBox.setOnCheckedChangeListener(null) // Important
        holder.checkBox.isChecked = task.isCompleted
        holder.bind(task)

        // Change background color of custom rows according to priority.
        when(task.priority){
            "Low" -> holder.itemView.setBackgroundColor(Color.rgb(120, 196,190))
            "High" -> holder.itemView.setBackgroundColor(Color.rgb(198, 141, 201))
            "Crucial" -> holder.itemView.setBackgroundColor(Color.rgb(230,57,96))
            else -> holder.itemView.setBackgroundColor(Color.WHITE)
        }

        // Click current item (Custom Row items) to go update fragment scene
        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(task)
            holder.itemView.findNavController().navigate(action)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return taskList.size
    }

}