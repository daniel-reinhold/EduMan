package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import com.eduman.data.room.repository.CoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * This class represents the ViewModel for all unspecific queries
 */
@HiltViewModel
class CoreViewModel @Inject constructor(
    val repository: CoreRepository
) : ViewModel() {

    /**
     * This method queries the grade-and test count for a specific subject
     * @param subjectId The ID if the subject - [Int]
     * @return A [GradeAndTestCount] wrapped in LiveData
     */
    fun checkGradeAndTestCount(subjectId: Int) = repository.checkGradeAndTestCount(subjectId)

}