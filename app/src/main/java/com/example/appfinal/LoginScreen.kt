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
import androidx.compose.ui.text.TextStyle
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
fun LoginScreen(onNavigateHome: (id: Int) -> Unit,
                onNavigateCadastroUsuario: () -> Unit) {
    val darkYellow = Color(0xFFA56F00)
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
        Image( //imagem
            painter = painterResource(id = R.drawable.viagpng),
            contentDescription = "",
            modifier = Modifier.size(280.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField( //campo usuário
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = {
                Text("Usuário",
                style = TextStyle(color = Color.DarkGray)
                )},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray
            )
        )
        OutlinedTextField( //campo senha
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Senha",
                      style = TextStyle(color = Color.DarkGray)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray
            ),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button( //botão entrar
            onClick = {
                viewModel.validateLogin(onResult = {
                    Log.i("Login", "Result ${it}")
                    if (it){
                        val idAux = viewModel.buscarIdUser(viewModel.name)
                        if (idAux != null) {
                            onNavigateHome(idAux)
                        }
                    }
                })
            },
            modifier = Modifier
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = darkYellow),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Entrar")
        }

        TextButton( //botão cadastrar
            onClick = { onNavigateCadastroUsuario() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent,
                                                     contentColor = Color.Black)
        ) {
            Text(text = "Criar novo usuário")
        }
    }
}