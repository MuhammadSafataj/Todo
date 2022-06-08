package com.safataj.todo.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.safataj.todo.data.model.Task
import com.safataj.todo.databinding.FragmentUndoneTasksBinding
import com.safataj.todo.ui.view.adapter.TaskAdapter
import com.safataj.todo.ui.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UndoneTasksFragment : Fragment() {

    private var _binding: FragmentUndoneTasksBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUndoneTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener = object : TaskAdapter.OnItemClickListener {
            override fun onClick(task: Task) {
                taskViewModel.editTask(task)
            }

            override fun onEdit(task: Task) {
            }

            override fun onDelete(task: Task) {
                taskViewModel.deleteTask(task)
            }

        }

        val adapter = TaskAdapter(listener)
        binding.recycler.adapter = adapter

        taskViewModel.undoneTasks.observe(viewLifecycleOwner) { tasks ->

            adapter.setData(tasks)

            if (tasks.isEmpty()) {
                binding.layoutMessage.visibility = View.VISIBLE
                binding.recycler.visibility = View.GONE
            } else {
                binding.layoutMessage.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}