package com.example.appfinal.repository

import com.example.appfinal.dao.ViagemDao
import com.example.appfinal.entity.Viagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViagemRepository(private val viagemDao: ViagemDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addViagem(viagem: Viagem){
        coroutine.launch(Dispatchers.IO) {
            viagemDao.insert(viagem)
        }
    }

    suspend fun getAllTravels(userId: Int): List<Viagem> {
        return withContext(Dispatchers.IO) {
            viagemDao.findAllByUserId(userId)
        }
    }

    fun attViagem(id: Int,  orcamento: Float){
        coroutine.launch(Dispatchers.IO){
            viagemDao.adicionarDespesa(id,orcamento)
        }
    }
}