package com.eduman.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.Constants.Companion.KEY_SUBJECT
import com.eduman.core.util.GeneralUtil
import com.eduman.data.room.entitiy.Subject
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class SubjectsPreviewAdapter(
    var subjects: List<Subject>
) : RecyclerView.Adapter<SubjectsPreviewAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view as MaterialCardView
        val colorView: View = view.findViewById(R.id.rviSubjectColorView)
        val textViewSubjectName: MaterialTextView = view.findViewById(R.id.rviSubjectTextViewSubjectTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_subject_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val subject = subjects[holder.adapterPosition]

        holder.textViewSubjectName.text = subject.title
        holder.colorView.backgroundTintList = GeneralUtil.getColorStateList(subject.color)

        holder.card.setOnClickListener {
            holder.card.findNavController().navigate(
                R.id.action_subjectFragment_to_subjectDetailFragment,
                bundleOf(KEY_SUBJECT to subject)
            )
        }
    }

    override fun getItemCount() = subjects.size

}