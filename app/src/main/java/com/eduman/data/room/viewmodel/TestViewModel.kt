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

    /**
     * This method queries all tests for a specific subject
     * @param subjectId The ID of the subject - [Int]
     * @return [List]<[Test]> wrapped in LiveData
     */
    fun getAll(subjectId: Int) = repository.getAll(subjectId)

    /**
     * This method queries an specific amount of tests for a specific subject
     * ordered ascending by "date"
     * @param subjectId The ID of the subject - [Int]
     * @param amount The amount of tests - [Int]
     * @return [List]<[Test]> wrapped in LiveData
     */
    fun getNext(subjectId: Int, amount: Int) = repository.getNext(subjectId, amount)

    /**
     * This method queries an specific amount of tests ordered ascending by "date"
     * @param amount The amount of tests - [Int]
     * @return [List]<[Test]> wrapped in LiveData
     */
    fun getNext(amount: Int) = repository.getNext(amount)

    /**
     * This method queries the grade for a specific test
     * @param testId The ID of the test - [Int]
     * @return TestAndGrade wrapped in LiveData
     */
    fun getGrade(testId: Int) = repository.getGrade(testId)

    /**
     * This method creates a new test
     * @param test The new test - [Test]
     */
    fun insert(test: Test) = viewModelScope.launch {
        repository.insert(test)
    }

    /**
     * This method deletes an existing test
     * @param test The test to delete - [Test]
     */
    fun delete(test: Test) = viewModelScope.launch {
        repository.delete(test)
    }

}