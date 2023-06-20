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
    isItemSelected: Boolean,
    viewModel: RegisterNewViagemViewModel,
    onCardClick: (Viagem) -> Unit,
    onNavigateHome: () -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick(viagens) },
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
    /*if (isItemSelected) {
        //ListarDespesas(1, onNavigateHome)
        moreExpenses(viagens, viewModel = viewModel)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    val newValor: Float = try {
                        viewModel.orcamento.toFloat()
                    } catch (e: NumberFormatException) {
                        0
                        0f
                    }
                    viewModel.AttExpenses(viagens.id, calcularExpense(viagens.orcamento, newValor))
                    onNavigateHome()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(150.dp)
            ) {
                Text("Atualizar")
            }
        }
    }*/

}

fun calcularExpense(oldExpense: Float, newExpense: Float): Float {
    if (newExpense != 0.0f) {
        return oldExpense + newExpense
    } else {
        return oldExpense
    }
}

fun formatFloat(number: Float): String {
    return String.format("%.2f", number)
}

@Composable
fun moreExpenses(viagens: Viagem, viewModel: RegisterNewViagemViewModel) {
    var orcamento = viagens.orcamento
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = orcamento.toString(),
            onValueChange = { orcamento = it.toFloatOrNull() ?: 0f },
            label = { Text("Despesa atual") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {

                }
            ),
            modifier = Modifier.padding(16.dp),
            enabled = false
        )
        OutlinedTextField(
            value = viewModel.orcamento,
            onValueChange = { viewModel.orcamento = (it.toFloatOrNull() ?: 0f) as String },
            label = { Text("Nova Despesa") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {

                }
            ),
            modifier = Modifier.padding(16.dp)
        ).toString()
    }

}

@Composable
fun ListaViagens(userID: String, onNavigateHome:() -> Unit) {
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
                0 -> R.drawable.negocios
                else -> R.drawable.lazer
            }
            TelaViagens(viagem, iconReason, idViagemSelecionada.value != null, viewModel, onCardClick = {
                idViagemSelecionada.value = it.id
            },
                onNavigateHome = { idViagemSelecionada.value = null })
        }
    }
}