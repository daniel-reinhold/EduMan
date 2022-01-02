package com.eduman.data.room.viewmodel

import androidx.lifecycle.ViewModel
import com.eduman.data.room.repository.CoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    val repository: CoreRepository
) : ViewModel() {

    fun checkGradeAndTestCount(subjectId: Int) = repository.checkGradeAndTestCount(subjectId)

}