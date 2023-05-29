package com.example.appfinal

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.entity.Viagem
import com.example.appfinal.viewModel.RegisterNewViagemViewModel
import com.example.appfinal.viewModel.RegisterNewViagemViewModelFactory

@Composable
fun TelaViagens(){
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewViagemViewModel = viewModel(
        factory = RegisterNewViagemViewModelFactory(application)
    )
    val viagens = listOf(
        Viagem("Viagem 1", "01/01/2023", "10/01/2023", 1000.0),
        Viagem("Viagem 2", "15/02/2023", "20/02/2023", 1500.0),
        Viagem("Viagem 3", "05/03/2023", "12/03/2023", 2000.0)
    )

    LazyColumn {
        items(viagens) { viagem ->
            ViagemItem(viagem) {
                // Ação para abrir outra tela com detalhes da viagem
            }
        }
    }
}

@Composable
fun ViagemItem(viagem: Viagem, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke() }
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Destino: ${viagem.destino}",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(16.dp)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Data inicial: ${viagem.dataInicio}")
                    Text(text = "Data final: ${viagem.dataFim}")
                }
                Text(
                    text = "Orçamento: ${viagem.orcamento}",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )
                Button(
                    onClick = { onItemClick.invoke() },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .height(IntrinsicSize.Min)
                        .width(70.percent)
                ) {
                    Text(text = "Detalhes")
                }
            }
        }
    }
}


