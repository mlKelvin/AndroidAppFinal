package com.example.appfinal.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appfinal.database.AppDatabase
import com.example.appfinal.repository.ViagemRepository

class RegisterNewViagemViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).viagemDao()
        val viagemRepository = ViagemRepository(dao)
        return RegisterNewViagemViewModel(viagemRepository) as T
    }
}
