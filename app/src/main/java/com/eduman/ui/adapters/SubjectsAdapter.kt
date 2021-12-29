package com.eduman.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.GeneralUtil
import com.eduman.data.room.entitiy.Subject
import com.eduman.ui.fragments.subject.SubjectFragmentDirections
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class SubjectsAdapter(
    var subjects: List<Subject>,
    private val navController: NavController?
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
        holder.colorView.backgroundTintList = GeneralUtil.getColorStateList(subject.color)

        holder.card.setOnClickListener {
            navController?.navigate(
                SubjectFragmentDirections.actionSubjectFragmentToSubjectDetailFragment(subject)
            )
        }
    }

    override fun getItemCount() = subjects.size

}