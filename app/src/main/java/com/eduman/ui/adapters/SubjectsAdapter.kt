package com.eduman.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.data.room.entitiy.Subject
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class SubjectsAdapter(
    private val subjects: List<Subject>
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
        val subject = subjects[holder.adapterPosition]

        holder.textViewSubjectName.text = subject.title
    }

    override fun getItemCount() = subjects.size

}