package com.example.appfinal.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinal.entity.User
import com.example.appfinal.entity.Viagem
import com.example.appfinal.repository.UserRepository
import com.example.appfinal.repository.ViagemRepository
import kotlinx.coroutines.launch

class RegisterNewViagemViewModel(private val viagemRepository: ViagemRepository): ViewModel() {

    var destino by mutableStateOf("")
    var dataInicio by mutableStateOf("")
    var dataFim by mutableStateOf("")
    var orcamento by mutableStateOf("")

    fun registrar() {
        val orcamentoFloat = orcamento.toFloatOrNull() ?: 0.0f
        val newViagem = Viagem(destino = destino, dataInicio = dataInicio, dataFim = dataFim, orcamento = orcamentoFloat)
        viagemRepository.addViagem(newViagem)
    }
}
