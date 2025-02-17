package com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun insertNotes(note : Notes)

    @Delete
    suspend fun deleteNotes(note : Notes)

    @Query("SELECT * FROM Notes")
    fun getNotes() : Flow<List<Notes>>

    @Query("SELECT * FROM Notes WHERE id = :id")
    suspend fun getNotes(id : Int) : Notes?

}