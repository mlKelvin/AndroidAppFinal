package com.example.appfinal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appfinal.entity.Viagem

@Dao
interface ViagemDao {

    @Insert
    suspend fun insert(viagem: Viagem)

    @Update
    suspend fun update(viagem: Viagem)

    @Query("UPDATE Viagem SET orcamento = :newOrcamento WHERE id = :id ")
    suspend fun incrementExpenses(id: Int, newOrcamento: Float)

    @Delete
    suspend fun delete(viagem: Viagem)

    @Query("select * from viagem v order by v.destino")
    suspend fun findAll(): List<Viagem>

    @Query("select * from viagem t where t.userID = :userId order by t.dataInicio")
    suspend fun findAllByUserId(userId: Int): List<Viagem>

    @Query("select * from viagem v where v.destino = :destino")
        suspend fun findByDestino(destino: String): Viagem?
}