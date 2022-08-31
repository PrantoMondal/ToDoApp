package com.studioartagonist.todoapppranto.repos


import com.studioartagonist.todoapppranto.daos.TodoDao

import com.studioartagonist.todoapppranto.entities.TodoModel

class TodoRepository(val todoDao: TodoDao) {

    suspend fun insertTodo(todoModel: TodoModel) {
        todoDao.insertTodo(todoModel)
    }

    fun getTodoByUserId(userId: Long) = todoDao.getTodosByUserId(userId)
}