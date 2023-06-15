package com.example.appfinal

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.entity.Viagem
import com.example.appfinal.viewModel.RegisterNewViagemViewModel
import com.example.appfinal.viewModel.RegisterNewViagemViewModelFactory

@Composable
fun TelaViagens(
    viagens: Viagem,
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
            Row(modifier = Modifier.padding(4.dp)) {
                Column {
                    Text(
                        text = viagens.destino,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = "${viagens.dataInicio} --> ${viagens.dataFim}",
                        style = MaterialTheme.typography.subtitle2
                    )
                    Text(
                        text = "Valor Total: ${formatFloat(viagens.orcamento)}R$",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }

        }

    }
    if (isItemSelected) {
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
                    viewModel.updateExpenses(viagens.id, calculateExpense(viagens.orcamento,viewModel.orcamento))
                    onNavigateHome()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(150.dp)
            ) {
                Text("Atualizar")
            }
        }
    }

}

fun formatFloat(number: Float): String {
    return String.format("%.2f", number)
}

fun calculateExpense(oldExpense: Float, newExpense: Float): Float {
    if (newExpense != 0.0f) {
        return oldExpense + newExpense
    } else {
        return oldExpense
    }
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
            value = viewModel.orcamento.toString(),
            onValueChange = { viewModel.orcamento = it.toFloatOrNull() ?: 0f },
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

    val selectedTravelId = remember { mutableStateOf<Int?>(null) }

    viewModel.getTravels(Integer.parseInt(userID))
    val travels by viewModel.travels.collectAsState()


    LazyColumn() {
        items(items = travels.filter { selectedTravelId.value == null || it.id == selectedTravelId.value }) { travel ->
            val iconReason = when (travel.reason) {
                0 -> R.drawable.bussines
                else -> R.drawable.lazer
            }
            screen(travel, iconReason, selectedTravelId.value != null, viewModel, onCardClick = {
                selectedTravelId.value = it.id
            },
                onNavigateHome = { selectedTravelId.value = null })
        }
    }
}