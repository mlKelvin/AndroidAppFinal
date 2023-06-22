package com.example.appfinal

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.CustomColors.darkYellow
import com.example.appfinal.entity.DespesaViagem
import com.example.appfinal.viewModel.RegisterNewDespesaViagemViewModel
import com.example.appfinal.viewModel.RegisterNewDespesaViagemViewModelFactory
import com.example.appfinal.viewModel.RegisterNewViagemViewModel
import com.example.appfinal.viewModel.RegisterNewViagemViewModelFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListarDespesas(onBackNavigate: () -> Unit, viagemId: Int, vNavController: NavController) {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewDespesaViagemViewModel = viewModel(
        factory = RegisterNewDespesaViagemViewModelFactory(application))
    viewModel.getDespesaViagens(viagemId)
    val viewModelViagem: RegisterNewViagemViewModel = viewModel(
        factory = RegisterNewViagemViewModelFactory(application)
    )
    val despesaViagens by viewModel.despesasViagem.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastre uma nova despesa") },
                backgroundColor = darkYellow,
                navigationIcon = {
                }
            )
        }
    ) {
        Column() {
            Column() {
                OutlinedTextField(
                    //Descrição despesa
                    value = viewModel.descricao,
                    onValueChange = { viewModel.descricao = it },
                    label = {
                        Text(
                            "Descrição",
                            style = TextStyle(color = Color.DarkGray)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.DarkGray
                    ),
                )

                OutlinedTextField(
                    //Valor despesa
                    value = viewModel.valor,
                    onValueChange = { viewModel.valor = it },
                    label = {
                        Text(
                            "Valor",
                            style = TextStyle(color = Color.DarkGray)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.DarkGray
                    ),
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button( //botão cadastrar
                    onClick = {
                        viewModel.registrar(viagemId = viagemId)
                        viewModelViagem.AttOrcamento(viagemId, viewModel.valor.toFloat())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(280.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = darkYellow),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = "Cadastrar Despesa")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button( //botão voltar
                    onClick = {
                        onBackNavigate()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(280.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = darkYellow),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = "Voltar")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column() {
                CarregarLazyColumn(items = despesaViagens)
            }
        }
    }
}

@Composable
fun CarregarLazyColumn(items: List<DespesaViagem>) {
    LazyColumn {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 8.dp,
                backgroundColor = Color.White,
            ) {
                Row {
                    Row(modifier = Modifier.padding(4.dp)) {
                        Column {
                            Text(
                                text = "Valor: " + item.valor.toString(),
                                style = MaterialTheme.typography.subtitle1
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Descrição: " + item.descricao,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                    }
                }
            }
        }
    }
}