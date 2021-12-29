package com.eduman.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.data.room.entitiy.Grade
import com.google.android.material.textview.MaterialTextView

class GradesAdapter(
    var grades: List<Grade>
) : RecyclerView.Adapter<GradesAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewGrade: MaterialTextView = view.findViewById(R.id.rviGradeTextViewGrade)
        val textViewWeighting: MaterialTextView = view.findViewById(R.id.rviGradeTextViewWeighting)
        val textViewTotal: MaterialTextView = view.findViewById(R.id.rviGradeTextViewTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_grade,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val grade = grades[holder.adapterPosition]

        holder.textViewGrade.text = grade.grade.toString()
        holder.textViewWeighting.text = grade.weighting.toString()
        holder.textViewTotal.text = (grade.grade * grade.weighting).toString()
    }

    override fun getItemCount() = grades.size

}