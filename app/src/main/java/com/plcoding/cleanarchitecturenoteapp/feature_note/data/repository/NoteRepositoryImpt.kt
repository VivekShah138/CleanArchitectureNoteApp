package com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository

import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDao
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Notes
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpt(
    private val noteDao: NoteDao
) : NoteRepository{

    override suspend fun insertNotes(note: Notes) {
        noteDao.insertNotes(note)
    }

    override suspend fun deleteNotes(note: Notes) {
        noteDao.deleteNotes(note)
    }

    override fun getNotes(): Flow<List<Notes>> {
        return noteDao.getNotes()
    }

    override suspend fun getNotes(id: Int): Notes? {
        return noteDao.getNotes(id)
    }
}