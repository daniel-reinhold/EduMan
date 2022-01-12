package com.eduman.ui.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.formatter.DateTimeFormatter
import com.eduman.data.room.entity.Test
import com.eduman.ui.adapters.diffcallback.TestsDiffCallback
import com.google.android.material.textview.MaterialTextView

class TestsPreviewAdapter(
    private val callback: Callback
) : RecyclerView.Adapter<TestsPreviewAdapter.AdapterViewHolder>() {
    private var list = listOf<Test>()

    interface Callback {
        fun onClick(test: Test)
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
        val root: LinearLayout = view as LinearLayout
        val textViewTopic: MaterialTextView = view.findViewById(R.id.rviTestPreviewTextViewTopic)
        val textViewDate: MaterialTextView = view.findViewById(R.id.rviTestPreviewTextViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_test_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val test = list[holder.adapterPosition]

        holder.textViewTopic.text = test.topic
        holder.textViewDate.text = DateTimeFormatter.formatDateTimeDefault(holder.context, test.date)

        holder.root.setOnClickListener {
            callback.onClick(test)
        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Test>) {
        val result = DiffUtil.calculateDiff(TestsDiffCallback(this.list, updatedList))
        this.list = updatedList
        result.dispatchUpdatesTo(this)
    }

}