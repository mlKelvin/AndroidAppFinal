package com.example.appfinal

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appfinal.entity.Viagem
import com.example.appfinal.viewModel.RegisterNewViagemViewModel
import com.example.appfinal.viewModel.RegisterNewViagemViewModelFactory

@Composable
fun TelaViagens(
    viagens: Viagem,
    iconReason: Int,
    onCardClick: (Viagem) -> Unit,
    vNavController: NavController,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCardClick(viagens)
                vNavController.navigate("despesa_viagem/${viagens.id}")},
        elevation = 8.dp,
        backgroundColor = Color.White,
    ) {
        Row {
            Image(
                painter = painterResource(iconReason),
                contentDescription = "Icone viagens",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Row(modifier = Modifier.padding(4.dp)) {
                Column {
                    Text(
                        text = viagens.destino,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = "${viagens.dataInicio} à ${viagens.dataFim}",
                        style = MaterialTheme.typography.subtitle2
                    )
                    Text(
                        text = "Valor total: ${formatFloat(viagens.orcamento)}R$",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}

fun formatFloat(number: Float): String {
    return String.format("%.2f", number)
}

@Composable
fun ListaViagens(userID: String, onNavigateHome:() -> Unit, vNavController: NavController) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewViagemViewModel = viewModel(
        factory = RegisterNewViagemViewModelFactory(application)
    )

    val idViagemSelecionada = remember { mutableStateOf<Int?>(null) }

    viewModel.getViagens(Integer.parseInt(userID))
    val viagens by viewModel.viagens.collectAsState()

    LazyColumn() {
        items(items = viagens.filter { idViagemSelecionada.value == null || it.id == idViagemSelecionada.value }) { viagem ->
            val iconReason = when (viagem.razao) {
                0 -> R.drawable.lazer
                else -> R.drawable.negocios
            }
            TelaViagens(viagem,
                        iconReason,
                        onCardClick = {idViagemSelecionada.value = it.id},
                        vNavController)
        }
    }
}
