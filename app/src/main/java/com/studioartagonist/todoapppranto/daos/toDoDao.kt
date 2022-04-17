package com.studioartagonist.todoapppranto.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.studioartagonist.todoapppranto.db.ToDoDB


@Dao
interface toDoDao {


        @Insert
        suspend fun addToDo(ToDoDB: ToDoDB)

//        @Update
//        suspend fun updateSchedule(busSchedule: BusSchedule)
//
        @Delete
        suspend fun deleteToDo(ToDoDB: ToDoDB)
    @Query("select * from tbl_todo")
    fun getAllToDo() : LiveData<List<ToDoDB>>
//
//        @Query("select * from tbl_schedule")
//        fun getAllSchedule() : LiveData<List<BusSchedule>>
//
//        @Query("select * from tbl_schedule where id = :id")
//        fun getScheduleById(id: Long) : LiveData<BusSchedule>
    }

