package com.studioartagonist.todoapppranto.daos

import androidx.room.*
import com.studioartagonist.todoapppranto.entities.UserModel

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userModel : UserModel) : Long

    @Delete
    suspend fun deleteUser(userModel : UserModel)

    @Update
    suspend fun updateUser(userModel : UserModel)

    @Query("select * from tbl_user where email = :email")
    suspend fun getUserByEmail(email:String) : UserModel?
}