package com.example.appfinal.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinal.entity.User
import com.example.appfinal.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterNewUserViewModel(private val userRepository: UserRepository): ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var passoword by mutableStateOf("")

    fun registrar() {
        val newUser = User(name = name, email = email, password = passoword)
        userRepository.addUser(newUser)
    }
}
