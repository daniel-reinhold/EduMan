package com.eduman.data.room.entitiy.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.entitiy.Test

class TestAndGrade(
    @Embedded
    val test: Test,

    @Relation(
        parentColumn = "id",
        entityColumn = "test_id"
    )
    val grade: Grade?
)