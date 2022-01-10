package com.eduman.ui.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.core.util.GradeUtil
import com.eduman.data.room.entitiy.Grade
import com.eduman.ui.adapters.diffcallback.GradesDiffCallback
import com.google.android.material.textview.MaterialTextView

class GradesAdapter : RecyclerView.Adapter<GradesAdapter.AdapterViewHolder>() {

    private var list = listOf<Grade>()

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context: Context = view.context
        val textViewGrade: MaterialTextView = view.findViewById(R.id.rviGradeTextViewGrade)
        val textViewWeighting: MaterialTextView = view.findViewById(R.id.rviGradeTextViewWeighting)
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
        val grade = list[holder.adapterPosition]

        holder.textViewGrade.apply {
            this.text = GradeUtil.formatGrade(grade.grade)
            this.setTextColor(
                ContextCompat.getColor(holder.context, GradeUtil.gradeToColor(grade.grade))
            )
        }
        holder.textViewWeighting.text = holder.context.getString(
            R.string.weighting_value,
            GradeUtil.formatGrade(grade.weighting)
        )
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Grade>) {
        val diffResult = DiffUtil.calculateDiff(GradesDiffCallback(this.list, updatedList))
        this.list = updatedList
        diffResult.dispatchUpdatesTo(this)
    }

}