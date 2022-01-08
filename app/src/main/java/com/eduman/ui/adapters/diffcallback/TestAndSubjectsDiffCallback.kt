package com.eduman.ui.adapters.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.eduman.data.room.entitiy.Test
import com.eduman.data.room.entitiy.relation.TestAndSubject

class TestAndSubjectsDiffCallback(
    private val oldList: List<TestAndSubject>,
    private val newList: List<TestAndSubject>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]

        return (
            (oldListItem.test.id ?: 0) == (newListItem.test.id?: 0) &&
            (oldListItem.subject.id ?: 0) == (newListItem.subject.id ?: 0)
        )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}