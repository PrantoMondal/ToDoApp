package com.studioartagonist.todoapppranto.db

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.studioartagonist.todoapppranto.entities.TodoModel
import com.studioartagonist.todoapppranto.entities.UserModel
import com.tanvir.training.todoappbatch04.daos.TodoDao
import com.tanvir.training.todoappbatch04.daos.UserDao


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


