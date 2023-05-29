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
fun TelaNovo() {
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewViagemViewModel = viewModel(
        factory = RegisterNewViagemViewModelFactory(application)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cadastre uma nova viagem") },
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
                label = { Text("Destino") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.dataInicio,
                onValueChange = { viewModel.dataInicio = it },
                label = { Text("Data Inicial") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.dataFim,
                onValueChange = { viewModel.dataFim = it },
                label = { Text("Data Final") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.orcamento,
                onValueChange = { viewModel.orcamento = it },
                label = { Text("Orçamento") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.registrar()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Confirmar")
            }
        }
    }
}