package com.tanvir.training.todoappbatch04.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.studioartagonist.todoapppranto.entities.UserModel

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userModel: UserModel)

    @Delete
    suspend fun deleteUser(userModel: UserModel)

    @Update
    suspend fun updateUser(userModel: UserModel)
}