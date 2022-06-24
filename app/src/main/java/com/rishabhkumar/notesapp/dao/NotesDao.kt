package com.rishabhkumar.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rishabhkumar.notesapp.model.Notes

//Data Access object
@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note:Notes)

    @Query("DELETE FROM Notes WHERE id = :id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(note:Notes)
}