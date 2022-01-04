package com.eduman.ui.adapters.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.eduman.data.room.entitiy.Grade

class GradesDiffCallback(
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