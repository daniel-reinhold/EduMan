package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entity.Grade
import com.eduman.data.room.repository.GradeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class represents the ViewModel for the "grades" table
 */
@HiltViewModel
class GradeViewModel @Inject constructor(
    private val repository: GradeRepository
) : ViewModel() {

    /**
     * This method queries all grades for a specific subject
     * @param subjectId The ID of the subject - [Int]
     * @return [List]<[Grade]> wrapped in LiveData
     */
    fun getAll(subjectId: Int) = repository.getAll(subjectId)

    /**
     * This method queries an specific amount of grades for a specific subject
     * ordered descending by "created_at"
     * @param subjectId The ID of the subject - [Int]
     * @param amount The amount of grades - [Int]
     * @return [List]<[Grade]> wrapped in LiveData
     */
    fun getLast(subjectId: Int, amount: Int) = repository.getLast(subjectId, amount)

    /**
     * This method creates a new grade
     * @param grade The new grade - [Grade]
     */
    fun insert(grade: Grade) = viewModelScope.launch {
        repository.insert(grade)
    }

    /**
     * This method deletes an existing grade
     * @param grade The grade to delete - [Grade]
     */
    fun delete(grade: Grade) = viewModelScope.launch {
        repository.delete(grade)
    }

}