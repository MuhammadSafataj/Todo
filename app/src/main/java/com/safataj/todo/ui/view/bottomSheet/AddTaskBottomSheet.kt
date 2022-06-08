package com.safataj.todo.ui.view.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.safataj.todo.data.model.Task
import com.safataj.todo.databinding.BottomSheetAddTaskBinding
import com.safataj.todo.ui.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskBottomSheet: BottomSheetDialogFragment() {

    private var _binding: BottomSheetAddTaskBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            if (binding.editTitle.text.isBlank()) {
                Toast.makeText(requireContext(), "فیلد عنوان پر نمایید", Toast.LENGTH_LONG).show()
            } else {
                addTask()
            }
        }
    }

    private fun addTask() {
        val timestamp = System.currentTimeMillis()
        val task = Task(
            title = binding.editTitle.text.toString(),
            timestamp = timestamp
        )

        taskViewModel.addTask(task)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}