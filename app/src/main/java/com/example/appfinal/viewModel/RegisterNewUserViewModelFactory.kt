package com.example.appfinal.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appfinal.database.AppDatabase
import com.example.appfinal.repository.UserRepository

class RegisterNewUserViewModelFactory(application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        AppDatabase.getDatabase(application)
        val userRepository = UserRepository
    }

}
