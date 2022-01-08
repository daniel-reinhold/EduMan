package com.eduman.data.room.entitiy.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.entitiy.Test

data class TestAndSubject(
    @Embedded
    val test: Test,

    @Relation(
        parentColumn = "subject_id",
        entityColumn = "id"
    )
    val subject: Subject
)