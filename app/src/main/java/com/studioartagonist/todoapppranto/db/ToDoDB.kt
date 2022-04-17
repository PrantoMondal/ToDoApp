package com.studioartagonist.todoapppranto.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "tbl_todo")
data class ToDoDB(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "user_id")
    val userId: String,
    val name:String,
    val priority : String,
    val date : String,
    val time : String,
    val completed : Boolean,
)



