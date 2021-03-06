package com.studioartagonist.todoapppranto.db

import android.content.Context
import androidx.room.*
import com.studioartagonist.todoapppranto.entities.TodoModel
import com.studioartagonist.todoapppranto.entities.UserModel
import com.studioartagonist.todoapppranto.daos.TodoDao
import com.studioartagonist.todoapppranto.daos.UserDao


@Database(entities = [TodoModel::class, UserModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
    abstract fun userDao() : UserDao

    companion object {
        private var db: TodoDatabase? = null
        fun getDB(context: Context) : TodoDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_db"
                ).build()
                return db!!
            }
            return db!!
        }
    }
}