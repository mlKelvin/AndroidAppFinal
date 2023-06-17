package com.example.appfinal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DespesaViagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val viagemID: Int,
    val descricao: String,
    val valor: Float,
) {

}