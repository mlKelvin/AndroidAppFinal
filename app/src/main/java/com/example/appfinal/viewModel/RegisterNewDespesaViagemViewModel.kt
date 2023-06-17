package com.example.appfinal.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinal.entity.DespesaViagem
import com.example.appfinal.entity.User
import com.example.appfinal.entity.Viagem
import com.example.appfinal.repository.DespesaViagemRepository
import com.example.appfinal.repository.UserRepository
import com.example.appfinal.repository.ViagemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterNewDespesaViagemViewModel(private val despesaViagemRepository: DespesaViagemRepository): ViewModel() {

    var valor by mutableStateOf("")
    var descricao by mutableStateOf("")
    var viagemID by  mutableStateOf(0)

    fun registrar(viagemId: Int) {
        val valorFloat = valor.toFloatOrNull() ?: 0.0f
        val newDespViagem = DespesaViagem(descricao = descricao, valor = valorFloat, viagemID = viagemId)
        despesaViagemRepository.addDespesaViagem(newDespViagem)
    }

    val despesasViagem: MutableStateFlow<List<DespesaViagem>> = MutableStateFlow(emptyList())

    fun getDespesaViagens(viagemID: Int) {
        viewModelScope.launch {
            val repoDespViagens = despesaViagemRepository.getAllDespesaViagem(viagemID)
            despesasViagem.value = repoDespViagens
        }
    }

}
