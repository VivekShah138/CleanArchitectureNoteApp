package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNotes(note : Notes)

    suspend fun deleteNotes(note : Notes)

    fun getNotes() : Flow<List<Notes>>

    suspend fun getNotes(id : Int) : Notes?



}