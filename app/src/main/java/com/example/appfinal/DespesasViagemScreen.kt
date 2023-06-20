package com.example.appfinal

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.entity.DespesaViagem
import com.example.appfinal.viewModel.RegisterNewDespesaViagemViewModel
import com.example.appfinal.viewModel.RegisterNewDespesaViagemViewModelFactory
import com.example.appfinal.viewModel.RegisterNewViagemViewModel
import com.example.appfinal.viewModel.RegisterNewViagemViewModelFactory

@Composable
fun TelaDespesasViagem(viagemId: Int,
                       viewModel: RegisterNewDespesaViagemViewModel,
                       //despesasViagens: DespesaViagem,
                       onNavigateViagens: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
            //.clickable { onCardClick(viagens) },
        elevation = 8.dp,
        backgroundColor = Color.White,
    ) {
        Row {
            Row(modifier = Modifier.padding(4.dp)) {
                Column {
                    Text(
                        text = viewModel.descricao,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = viewModel.descricao,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }

        }
    }
}

@Composable
fun ListarDespesas(viagemId: Int, onNavigateViagens:() -> Unit) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewDespesaViagemViewModel = viewModel(
        factory = RegisterNewDespesaViagemViewModelFactory(application)
    )
    //val idViagemSelecionada = remember { mutableStateOf<Int?>(null) }
    viewModel.getDespesaViagens(viagemId)
    val despesaViagens by viewModel.despesasViagem.collectAsState()



    LazyColumn() {
        items(items = despesaViagens) {
            TelaDespesasViagem(viagemId, viewModel, onNavigateViagens)
        }
    }
}