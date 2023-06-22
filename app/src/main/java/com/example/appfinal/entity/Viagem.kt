package com.example.appfinal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userID: Int,
    val destino: String,
    val dataInicio: String,
    val dataFim: String,
    val orcamento: Float,
    val motivo: Int
) {

}