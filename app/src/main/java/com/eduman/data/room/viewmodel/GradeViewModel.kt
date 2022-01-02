package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entitiy.Grade
import com.eduman.data.room.repository.GradeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GradeViewModel @Inject constructor(
    val repository: GradeRepository
) : ViewModel() {

    fun getAll(subjectId: Int) = repository.getAll(subjectId)

    fun getLast(subjectId: Int, amount: Int) = repository.getLast(subjectId, amount)

    fun insert(grade: Grade) = viewModelScope.launch {
        repository.insert(grade)
    }

    fun delete(grade: Grade) = viewModelScope.launch {
        repository.delete(grade)
    }

}