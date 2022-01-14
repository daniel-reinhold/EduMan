package com.eduman.ui.adapters.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.ui.adapters.diffcallback.TimetableDayDiffCallback
import com.eduman.ui.fragments.timetable.TimetableFragment

class TimetableDayAdapter : RecyclerView.Adapter<TimetableDayAdapter.AdapterViewHolder>() {

    private var list = listOf<TimetableFragment.Day>()

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.rviTimetableDayRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_timetable_day,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val adapter = TimetableSubjectAdapter()
        holder.recyclerView.adapter = adapter

        adapter.updateList(list[holder.adapterPosition].subjects)
    }

    override fun getItemCount() = TimetableFragment.DAYS_SHOWING

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<TimetableFragment.Day>) {
        val diffResult = DiffUtil.calculateDiff(TimetableDayDiffCallback(list, newList))
        this.list = newList
        diffResult.dispatchUpdatesTo(this)
    }

}