package com.eduman.ui.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.extensions.format
import com.eduman.data.room.entity.Grade
import com.eduman.ui.adapters.diffcallback.GradesDiffCallback
import com.google.android.material.textview.MaterialTextView

class GradesPreviewAdapter(
    private var list: List<Grade>
) : RecyclerView.Adapter<GradesPreviewAdapter.AdapterViewHolder>() {

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewGrade: MaterialTextView = view.findViewById(R.id.rviGradePreviewTextViewGrade)
        val textViewWeighting: MaterialTextView = view.findViewById(R.id.rviGradePreviewTextViewWeighting)
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
        val grade = list[holder.adapterPosition]

        holder.textViewGrade.text = grade.grade.format()
        holder.textViewWeighting.text = grade.weighting.format()
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Grade>) {
        val diffResult = DiffUtil.calculateDiff(GradesDiffCallback(this.list, updatedList))
        this.list = updatedList
        diffResult.dispatchUpdatesTo(this)
    }

}