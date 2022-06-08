package com.safataj.todo.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.play.core.tasks.Tasks
import com.safataj.todo.R
import com.safataj.todo.databinding.ActivityMainBinding
import com.safataj.todo.ui.view.bottomSheet.AddTaskBottomSheet
import com.safataj.todo.ui.view.fragment.AllTasksFragment
import com.safataj.todo.ui.view.fragment.DoneTasksFragment
import com.safataj.todo.ui.view.fragment.UndoneTasksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabItems = arrayOf("کار ها", "انجام شده", "انجام نشده")
        binding.pager.adapter = TasksAdapter(this)

        TabLayoutMediator(binding.tab, binding.pager) {tab, position ->
            tab.text = tabItems[position]
        }.attach()

        binding.fabAdd.setOnClickListener{
            AddTaskBottomSheet().show(supportFragmentManager, "AddTask")
        }
    }

    private inner class TasksAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> AllTasksFragment()
                1 -> DoneTasksFragment()
                else -> UndoneTasksFragment()
            }
        }

    }
}