package com.eduman.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.formatter.DateTimeFormatter
import com.eduman.data.room.entitiy.Test
import com.google.android.material.textview.MaterialTextView

class TestsPreviewAdapter(
    var tests: List<Test>
) : RecyclerView.Adapter<TestsPreviewAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
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
        val test = tests[holder.adapterPosition]

        holder.textViewTopic.text = test.topic
        holder.textViewDate.text = DateTimeFormatter.formatDateTimeDefault(holder.context, test.date)
    }

    override fun getItemCount() = tests.size

}