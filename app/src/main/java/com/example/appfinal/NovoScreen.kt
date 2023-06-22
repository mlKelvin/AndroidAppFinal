package com.example.appfinal

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.viewModel.LoginScreenViewModel
import com.example.appfinal.viewModel.LoginScreenViewModelFactory
import com.example.appfinal.viewModel.RegisterNewViagemViewModel
import com.example.appfinal.viewModel.RegisterNewViagemViewModelFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TelaNovo(userID: String) {
    val darkYellow = Color(0xFFA56F00)
    var OpSelect by remember { mutableStateOf(0) }
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewViagemViewModel = viewModel(
        factory = RegisterNewViagemViewModelFactory(application)
    )
    LaunchedEffect(OpSelect) {
        val razao = OpSelect
        viewModel.razao = razao
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastre uma nova viagem") },
                backgroundColor = darkYellow,
                navigationIcon = {
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.destino,
                onValueChange = { viewModel.destino = it },
                label = { Text("Destino",
                          style = TextStyle(color = Color.DarkGray)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray
                ),
            )

            OutlinedTextField(
                value = viewModel.dataInicio,
                onValueChange = { viewModel.dataInicio = it },
                label = { Text("Data Inicial",
                          style = TextStyle(color = Color.DarkGray)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray
                ),
            )

            OutlinedTextField(
                value = viewModel.dataFim,
                onValueChange = { viewModel.dataFim = it },
                label = { Text("Data Final",
                          style = TextStyle(color = Color.DarkGray)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray
                ),
            )

            OutlinedTextField(
                value = viewModel.orcamento,
                onValueChange = { viewModel.orcamento = it },
                label = { Text("Orçamento",
                          style = TextStyle(color = Color.DarkGray)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray
                ),
            )

            Row {
                RadioButton(
                    selected = OpSelect == 0,
                    onClick = { OpSelect = 0 },
                )
                Text("Lazer", Modifier.padding(start = 6.dp, top = 10.dp))

                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = OpSelect == 1,
                    onClick = { OpSelect = 1 },
                )
                Text("Trabalho", Modifier.padding(start = 6.dp, top = 10.dp))

            }

            Button(
                onClick = {
                    viewModel.registrar(Integer.parseInt(userID))
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = darkYellow)
            ) {
                Text(text = "Confirmar")
            }
        }
    }
}