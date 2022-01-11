package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entity.Subject
import com.eduman.data.room.repository.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class represents the ViewModel for the "subjects" table
 */
@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val repository: SubjectRepository
) : ViewModel() {

    /**
     * This method queries all subjects
     * @return [List]<[Subject]> wrapped in LiveData
     */
    fun getAll() = repository.getAll()

    /**
     * This method searches for a subject with a certain ID
     * @return [Subject]? wrapped in LiveData
     */
    fun find(id: Int) = repository.find(id)

    /**
     * This method queries subjects by their title
     * @param title The title of the subject to search for
     * @return [List]<[Subject]> wrapped in LiveData
     */
    fun searchByTitle(title: String) = repository.searchByTitle(title)

    /**
     * This method creates a new subject
     * @param subject The new subject - [Subject]
     */
    fun insert(subject: Subject) = viewModelScope.launch {
        repository.insert(subject)
    }

    /**
     * This method deletes an existing subject
     * @param subject The subject to delete - [Subject]
     */
    fun delete(subject: Subject) = viewModelScope.launch {
        repository.delete(subject)
    }

}