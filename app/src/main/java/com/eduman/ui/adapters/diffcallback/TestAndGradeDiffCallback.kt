package com.eduman.ui.adapters.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.eduman.data.room.entity.relation.TestAndGrade
import com.eduman.data.room.entity.relation.TestAndSubject

class TestAndGradeDiffCallback(
    private val oldList: List<TestAndGrade>,
    private val newList: List<TestAndGrade>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]

        return (
            (oldListItem.test.id ?: 0) == (newListItem.test.id ?: 0) &&
            (oldListItem.grade?.id ?: 0) == (newListItem.grade?.id ?: 0)
        )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}