package com.eduman.data.room.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.eduman.data.room.entity.Subject
import com.eduman.data.room.entity.Test

data class TestAndSubject(
    @Embedded
    val test: Test,

    @Relation(
        parentColumn = "subject_id",
        entityColumn = "id"
    )
    val subject: Subject
)