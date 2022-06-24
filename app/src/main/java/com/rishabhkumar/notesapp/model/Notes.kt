package com.rishabhkumar.notesapp.model

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey


//Entity class

@Entity(tableName = "Notes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var title: String,
    var subtitle: String,
    var notes:String,
    var date:String,
    var priority:String

)