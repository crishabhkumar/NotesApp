package com.rishabhkumar.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rishabhkumar.notesapp.Database.NotesDatabase
import com.rishabhkumar.notesapp.model.Notes
import com.rishabhkumar.notesapp.repositry.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val repo: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repo = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repo.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repo.getAllNotes()

    fun deleteNotes(id: Int) {
        repo.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repo.updateNotes(notes)
    }

}