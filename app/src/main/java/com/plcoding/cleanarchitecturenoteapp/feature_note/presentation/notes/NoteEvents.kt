package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Notes
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder

sealed class NoteEvents {

    data class Order(val noteOrder: NoteOrder) : NoteEvents()
    data class DeleteNode(val notes: Notes) : NoteEvents()
    object RestoreNote : NoteEvents()
    object ToggleOrderSection :NoteEvents()

}