package com.example.appfinal

import android.app.Application
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfinal.viewModel.RegisterNewUserViewModel
import com.example.appfinal.viewModel.RegisterNewUserViewModelFactory

@Composable
fun TelaCadastroUsuario(onBackNavigate: () -> Unit){
    val darkYellow = Color(0xFFA56F00)
    val application = LocalContext.current.applicationContext as Application
    val viewModel: RegisterNewUserViewModel = viewModel(
        factory = RegisterNewUserViewModelFactory(application)
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField( //campo usuário
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Usuário",
                      style = TextStyle(color = Color.DarkGray)
            ) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray
            ),
        )
        OutlinedTextField( //campo senha
            value = viewModel.passoword,
            onValueChange = { viewModel.passoword = it },
            label = { Text("Senha",
                      style = TextStyle(color = Color.DarkGray)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray
            ),
        )
        OutlinedTextField( //campo e-mail
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Endereço de e-mail",
                      style = TextStyle(color = Color.DarkGray)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button( //botão cadastrar
            onClick = {
                viewModel.registrar()
                onBackNavigate()
            },
            modifier = Modifier
                //.fillMaxWidth()
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = darkYellow),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Cadastrar")
        }
    }
}