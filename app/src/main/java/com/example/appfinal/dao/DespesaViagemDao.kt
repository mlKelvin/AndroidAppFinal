package com.example.appfinal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appfinal.entity.DespesaViagem
import com.example.appfinal.entity.Viagem

@Dao
interface DespesaViagemDao {

    @Insert
    suspend fun insert(despesaViagem: DespesaViagem)

    @Update
    suspend fun update(despesaViagem: DespesaViagem)

    @Delete
    suspend fun delete(despesaViagem: DespesaViagem)

    @Query("select * from despesaViagem v where v.viagemID = :viagemId")
    suspend fun findAllByViagemId(viagemId: Int): List<DespesaViagem>
}