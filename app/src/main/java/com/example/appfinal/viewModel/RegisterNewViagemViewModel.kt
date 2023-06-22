package com.example.appfinal.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinal.entity.User
import com.example.appfinal.entity.Viagem
import com.example.appfinal.repository.UserRepository
import com.example.appfinal.repository.ViagemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterNewViagemViewModel(private val viagemRepository: ViagemRepository): ViewModel() {

    var destino by mutableStateOf("")
    var dataInicio by mutableStateOf("")
    var dataFim by mutableStateOf("")
    var orcamento by mutableStateOf("")
    var motivo by  mutableStateOf(0)

    fun registrar(userId: Int) {
        val orcamentoFloat = orcamento.toFloatOrNull() ?: 0.0f
        val newViagem = Viagem(userID = userId, destino = destino, dataInicio = dataInicio, dataFim = dataFim, orcamento = orcamentoFloat, motivo = motivo)
        viagemRepository.addViagem(newViagem)
    }

    fun AttOrcamento(id: Int, orcamento: Float){
        viagemRepository.attViagem(id, orcamento)
    }

    val viagens: MutableStateFlow<List<Viagem>> = MutableStateFlow(emptyList())

    fun getViagens(userId: Int) {
        viewModelScope.launch {
            val repoViagens = viagemRepository.getAllTravels(userId)
            viagens.value = repoViagens
        }
    }

}
