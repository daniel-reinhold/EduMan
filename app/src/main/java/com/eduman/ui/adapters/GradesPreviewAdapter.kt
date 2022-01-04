package com.eduman.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.data.room.entitiy.Grade
import com.google.android.material.textview.MaterialTextView

class GradesPreviewAdapter(
    var grades: List<Grade>
) : RecyclerView.Adapter<GradesPreviewAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewGrade: MaterialTextView = view.findViewById(R.id.rviGradePreviewTextViewGrade)
        val textViewWeighting: MaterialTextView = view.findViewById(R.id.rviGradePreviewTextViewWeighting)
        val textViewTotal: MaterialTextView = view.findViewById(R.id.rviGradePreviewTextViewTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_grade_preview,
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

    fun updateList(updatedList: List<Grade>) {
        val diffResult = DiffUtil.calculateDiff(GradesPreviewDiffCallback(this.grades, updatedList))
        this.grades = updatedList
        diffResult.dispatchUpdatesTo(this)
    }

}

class GradesPreviewDiffCallback(
    private val oldList: List<Grade>,
    private val newList: List<Grade>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].id ?: 0) == (newList[newItemPosition].id ?: 0)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}