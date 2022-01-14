package com.eduman.ui.adapters.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.eduman.data.room.entity.Grade
import com.eduman.ui.fragments.timetable.TimetableFragment

class TimetableDayDiffCallback(
    private val oldList: List<TimetableFragment.Day>,
    private val newList: List<TimetableFragment.Day>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}