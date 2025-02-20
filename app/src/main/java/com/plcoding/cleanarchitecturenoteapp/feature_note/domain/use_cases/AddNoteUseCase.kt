package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_cases

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Notes
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    val repository: NoteRepository
) {

    suspend operator fun invoke(note : Notes){

        if(note.title.isBlank()){
            throw InvalidNoteException("Title Of the Note can't be empty")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("Content Of the Note can't be empty")
        }

        repository.insertNotes(note)
    }

}