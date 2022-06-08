package com.safataj.todo.ui.view.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.safataj.todo.data.model.Task
import com.safataj.todo.databinding.BottomSheetEditTaskBinding
import com.safataj.todo.ui.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskBottomSheet(private val task: Task) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetEditTaskBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTitle.setText(task.title)

        binding.buttonAdd.setOnClickListener {
            if (binding.editTitle.text.isBlank()) {
                Toast.makeText(requireContext(), "فیلد عنوان پر نمایید", Toast.LENGTH_LONG).show()
            } else {
                editTask()
            }
        }

    }

    private fun editTask() {

        task.title = binding.editTitle.text.toString()
        taskViewModel.editTask(task)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}