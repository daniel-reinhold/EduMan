package com.eduman.ui.adapters.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduman.R
import com.eduman.ui.fragments.timetable.TimetableFragment
import com.google.android.material.textview.MaterialTextView

class TimetableSubjectAdapter : RecyclerView.Adapter<TimetableSubjectAdapter.AdapterViewHolder>() {

    private var list = listOf<TimetableFragment.Subject>()

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewSubjectName: MaterialTextView = view.findViewById(R.id.rviTimetableSubjectAbbreviation)
        val textViewRoomName: MaterialTextView = view.findViewById(R.id.rviTimetableSubjectRoomName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rvi_timetable_subject,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val subject = list[holder.adapterPosition]

        holder.textViewSubjectName.text = subject.subjectName
        holder.textViewRoomName.text = subject.roomName
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<TimetableFragment.Subject>) {
        this.list = newList
        notifyDataSetChanged()
    }

}