package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Notes
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_cases.NoteUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCase: NoteUseCases
) : ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state

    private var noteToBeRestored : Notes? = null

    private var getNotesJob : Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(noteEvents: NoteEvents){
        when(noteEvents) {
            is NoteEvents.Order -> {

                if(state.value.noteOrder::class == noteEvents.noteOrder::class &&
                    state.value.noteOrder.orderType == noteEvents.noteOrder.orderType
                    ){
                    return
                }
                getNotes(noteEvents.noteOrder)

            }
            is NoteEvents.DeleteNode -> {
                viewModelScope.launch{
                    notesUseCase.deleteNoteUserCase(noteEvents.notes)
                    noteToBeRestored = noteEvents.notes
                }

            }
            is NoteEvents.RestoreNote -> {

                viewModelScope.launch {
                    notesUseCase.addNoteUseCase(noteToBeRestored ?: return@launch)
                    noteToBeRestored = null
                }

            }
            is NoteEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder : NoteOrder) {

        getNotesJob?.cancel()
        getNotesJob = notesUseCase.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)

    }

}