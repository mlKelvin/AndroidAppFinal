package com.example.appfinal.repository

import com.example.appfinal.dao.UserDao
import com.example.appfinal.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addUser(user: User){
        coroutine.launch(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    suspend fun findByName(name:String): User? = userDao.findByName(name)

    suspend fun findIdByName(name:String): Int? = userDao.findByName(name)?.id
}