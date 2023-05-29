package com.example.appfinal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //se for mudar nome tabela: @Entity(tableName = "user")
data class Viagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val destino: String,
    val dataInicio: String,
    val dataFim: String,
    val orcamento: Float
) {

}