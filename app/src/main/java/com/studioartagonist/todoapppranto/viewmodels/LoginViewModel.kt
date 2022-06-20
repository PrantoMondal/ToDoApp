package com.studioartagonist.todoapppranto.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studioartagonist.todoapppranto.db.TodoDatabase
import com.studioartagonist.todoapppranto.entities.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(application: Application)
    : AndroidViewModel(application) {
        private val userDao = TodoDatabase.getDB(application).userDao()
    val errMsgLD: MutableLiveData<String> = MutableLiveData()
    var userModel: UserModel? = null

    fun login(email: String, password:String,callback:(Long) -> Unit){
        viewModelScope.launch {
            userModel = userDao.getUserByEmail(email)
            if(userModel!=null){
                if (userModel!!.password == password){
                    callback(userModel!!.userId)
                }
                else{
                    errMsgLD.value = "Incorrect Password"
                }
            }
            else{
                errMsgLD.value = "Email does not exist"
            }
        }
    }
    fun register(user: UserModel,callback:(Long) -> Unit){
        viewModelScope.launch {
            userModel = userDao.getUserByEmail(user.email)
            if (userModel!=null){
                errMsgLD.value = "Email Already Exist"
            }
            else{
                val rowid = userDao.insertUser(user)
                userModel = user.apply {
                    userId = rowid
                }
                callback(rowid)
            }
        }
    }
}