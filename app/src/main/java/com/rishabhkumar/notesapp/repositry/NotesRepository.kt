package com.rishabhkumar.notesapp.repositry

import androidx.lifecycle.LiveData
import com.rishabhkumar.notesapp.dao.NotesDao
import com.rishabhkumar.notesapp.model.Notes

class NotesRepository (val dao: NotesDao){

    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes:Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes:Notes){
        dao.updateNotes(notes)
    }

}