package com.eduman.data.room.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.eduman.data.room.entity.Grade
import com.eduman.data.room.entity.Test

/**
 * This class represents a One-To-One relationship between a test and a grade
 * @property test The test - [Test]
 * @property grade The with the test associated grade - [Grade]?
 */
class TestAndGrade(
    @Embedded
    val test: Test,

    @Relation(
        parentColumn = "id",
        entityColumn = "test_id"
    )
    val grade: Grade?
)