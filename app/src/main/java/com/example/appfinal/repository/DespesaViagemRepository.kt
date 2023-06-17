package com.example.appfinal.repository

import com.example.appfinal.dao.DespesaViagemDao
import com.example.appfinal.dao.ViagemDao
import com.example.appfinal.entity.DespesaViagem
import com.example.appfinal.entity.Viagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DespesaViagemRepository(private val despesaViagemDao: DespesaViagemDao) {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addDespesaViagem(despesaViagem: DespesaViagem){
        coroutine.launch(Dispatchers.IO) {
            despesaViagemDao.insert(despesaViagem)
        }
    }

    suspend fun getAllDespesaViagem(ViagemID: Int): List<DespesaViagem> {
        return withContext(Dispatchers.IO) {
            despesaViagemDao.findAllByViagemId(ViagemID)
        }
    }
}