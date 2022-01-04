package com.eduman.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.util.GeneralUtil
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.entitiy.Test
import com.eduman.ui.adapters.diffcallback.SubjectsDiffCallback
import com.eduman.ui.adapters.diffcallback.TestsDiffCallback
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class SubjectsAdapter(
    private var list: List<Subject>
) : RecyclerView.Adapter<SubjectsAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view as MaterialCardView
        val colorView: View = view.findViewById(R.id.rviSubjectColorView)
        val textViewSubjectName: MaterialTextView = view.findViewById(R.id.rviSubjectTextViewSubjectTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_subject,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val subject = list[holder.adapterPosition]

        holder.textViewSubjectName.text = subject.title
        holder.colorView.backgroundTintList = GeneralUtil.getColorStateList(subject.color)

        holder.card.setOnClickListener {
            holder.card.findNavController().navigate(
                R.id.action_subjectFragment_to_subjectDetailFragment,
                bundleOf(KEY_SUBJECT to subject)
            )
        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Subject>) {
        val result = DiffUtil.calculateDiff(SubjectsDiffCallback(this.list, updatedList))
        this.list = updatedList
        result.dispatchUpdatesTo(this)
    }

}