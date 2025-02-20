package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_cases

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUserCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase
)