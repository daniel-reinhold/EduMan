package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduman.data.room.entitiy.Subject
import com.eduman.data.room.repository.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    val repository: SubjectRepository
) : ViewModel() {

    fun getAll() = repository.getAll()

    fun searchByTitle(title: String) = repository.searchByTitle(title)

    fun insert(subject: Subject) = viewModelScope.launch {
        repository.insert(subject)
    }

    fun delete(subject: Subject) = viewModelScope.launch {
        repository.delete(subject)
    }

}