package com.rishabhkumar.notesapp.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rishabhkumar.notesapp.dao.NotesDao

//functions will not contain their bodies
abstract class NotesDatabase : RoomDatabase() {
    abstract fun myNotesDao(): NotesDao

    //one type of static object
    companion object {
        //volatile means it can be easily accessible
        @Volatile
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes").build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }

    }
}