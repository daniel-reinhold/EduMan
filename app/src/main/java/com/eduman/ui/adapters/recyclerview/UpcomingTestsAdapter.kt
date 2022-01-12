package com.eduman.ui.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.formatter.DateFormatter
import com.eduman.data.room.entity.Test
import com.eduman.data.room.entity.relation.TestAndSubject
import com.eduman.ui.adapters.diffcallback.TestAndSubjectsDiffCallback
import com.google.android.material.textview.MaterialTextView

class UpcomingTestsAdapter(
    private val callback: Callback
) : RecyclerView.Adapter<UpcomingTestsAdapter.AdapterViewHolder>() {

    interface Callback {
        fun onClick(testAndSubject: TestAndSubject)
    }

    private var list = listOf<TestAndSubject>()

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
        val root: LinearLayout = view as LinearLayout
        val textViewTopic: MaterialTextView = view.findViewById(R.id.rviTestUpcomingTextViewTopic)
        val textViewDateAndSubject: MaterialTextView = view.findViewById(R.id.rviTestUpcomingTextViewDateAndSubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_test_upcoming,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val test = list[holder.adapterPosition]

        holder.textViewTopic.text = test.test.topic
        holder.textViewDateAndSubject.text = holder.context.getString(
            R.string.date_with_subject,
            DateFormatter.formatDateDefault(holder.context, test.test.date),
            test.subject.title
        )

        holder.root.setOnClickListener {
            callback.onClick(test)
        }
    }

    override fun getItemCount() = list.size

    fun updateList(newList: List<TestAndSubject>) {
        val diffCallbackResult = DiffUtil.calculateDiff(TestAndSubjectsDiffCallback(list, newList))
        list = newList
        diffCallbackResult.dispatchUpdatesTo(this)
    }

}