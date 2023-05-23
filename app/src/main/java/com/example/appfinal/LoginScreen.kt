package com.example.appfinal

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.appfinal.dao.UserDao
import com.example.appfinal.viewModel.LoginScreenViewModel
import com.example.appfinal.viewModel.LoginScreenViewModelFactory
import com.example.appfinal.viewModel.RegisterNewUserViewModel
import com.example.appfinal.viewModel.RegisterNewUserViewModelFactory

@Composable
fun LoginScreen(onNavigateHome: () -> Unit,
                onNavigateCadastroUsuario: () -> Unit) {

    val application = LocalContext.current.applicationContext as Application
    val viewModel: LoginScreenViewModel = viewModel(
        factory = LoginScreenViewModelFactory(application)
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var usuario by remember { mutableStateOf("") }
        var senha by remember { mutableStateOf("") }

        Image( //imagem
            painter = painterResource(id = R.drawable.viagpng),
            contentDescription = "",
            modifier = Modifier.size(280.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField( //campo usuário
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuário") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        OutlinedTextField( //campo senha
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button( //botão entrar
            onClick = {
                //focusManager.clearFocus()
                viewModel.validateLogin(onResult = {
                    Log.i("Login", "Result ${it}")
                    if (it){
                        onNavigateHome()
                    }
                })
            },
            modifier = Modifier
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Entrar")
        }

        TextButton( //botão cadastrar
            onClick = { onNavigateCadastroUsuario() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent)
        ) {
            Text(text = "Criar novo usuário")
        }
    }
}