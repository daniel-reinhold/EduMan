package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entitiy.Test
import com.eduman.data.room.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    val repository: TestRepository
) : ViewModel() {

    fun getAll(subjectId: Int) = repository.getAll(subjectId)

    fun getNext(subjectId: Int, amount: Int) = repository.getNext(subjectId, amount)

    fun getGrade(testId: Int) = repository.getGrade(testId)

    fun insert(test: Test) = viewModelScope.launch {
        repository.insert(test)
    }

    fun delete(test: Test) = viewModelScope.launch {
        repository.delete(test)
    }

}