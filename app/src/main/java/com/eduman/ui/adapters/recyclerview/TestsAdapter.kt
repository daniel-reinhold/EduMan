package com.eduman.ui.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.formatter.DateTimeFormatter
import com.eduman.data.room.entity.Test
import com.eduman.data.room.entity.relation.TestAndGrade
import com.eduman.ui.adapters.diffcallback.TestAndGradeDiffCallback
import com.eduman.ui.adapters.diffcallback.TestsDiffCallback
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class TestsAdapter(
    private val callback: Callback
) : RecyclerView.Adapter<TestsAdapter.AdapterViewHolder>() {

    interface Callback {
        fun onTestClicked(test: Test)
    }

    private var list = listOf<Test>()

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view as MaterialCardView
        val textViewTopic: MaterialTextView = view.findViewById(R.id.rviTestTextViewTopic)
        val textViewDate: MaterialTextView = view.findViewById(R.id.rviTestTextViewDate)

        val context: Context = card.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_test,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val test = list[holder.adapterPosition]

        holder.textViewTopic.text = test.topic
        holder.textViewDate.text = DateTimeFormatter.formatDateTimeDefault(holder.context, test.date)

        holder.card.setOnClickListener {
            callback.onTestClicked(test)
        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Test>) {
        val result = DiffUtil.calculateDiff(TestsDiffCallback(this.list, updatedList))
        this.list = updatedList
        result.dispatchUpdatesTo(this)
    }

}