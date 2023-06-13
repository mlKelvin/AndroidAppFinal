package com.example.appfinal.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appfinal.dao.UserDao
import com.example.appfinal.dao.ViagemDao
import com.example.appfinal.entity.User
import com.example.appfinal.entity.Viagem

@Database(entities = [User::class, Viagem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun viagemDao(): ViagemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(application: Application): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                application.applicationContext,
                AppDatabase ::class.java,
                "meu-db3"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}