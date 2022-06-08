package com.safataj.todo.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.safataj.todo.R
import com.safataj.todo.data.model.Task

class TaskAdapter(private val listener: OnItemClickListener):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var data: List<Task> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Task>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = data[position].title
        holder.radioButton.isChecked = data[position].isDone

        holder.radioButton.setOnClickListener {
            data[position].isDone = !data[position].isDone
            listener.onClick(data[position])
        }

        holder.buttonMore.setOnClickListener {
            val popupMenu = PopupMenu(holder.buttonMore.context, holder.buttonMore)
            popupMenu.menuInflater.inflate(R.menu.menu_task, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.item_delete -> listener.onDelete(data[position])
                    R.id.item_edit -> listener.onEdit(data[position])
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.text_title)
        val buttonMore: ImageButton = itemView.findViewById(R.id.button_more)
        val radioButton: CheckBox = itemView.findViewById(R.id.check_box)
    }

    interface OnItemClickListener {
        fun onClick(task: Task)

        fun onEdit(task: Task)

        fun onDelete(task: Task)
    }
}