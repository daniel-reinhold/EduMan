package com.eduman.ui.adapters.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.eduman.data.room.entity.Subject

class SubjectsDiffCallback(
    private val oldList: List<Subject>,
    private val newList: List<Subject>
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