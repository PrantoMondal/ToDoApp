package com.studioartagonist.todoapppranto.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.studioartagonist.todoapppranto.db.TodoDatabase
import com.studioartagonist.todoapppranto.entities.TodoModel
import com.studioartagonist.todoapppranto.repos.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDB(application).todoDao()
    private val repository = TodoRepository(todoDao)
    fun insertTodo(todoModel: TodoModel) {
        viewModelScope.launch {
            repository.insertTodo(todoModel)
        }
    }

    fun getTodoByUserId(userId: Long) = repository.getTodoByUserId(userId)
}