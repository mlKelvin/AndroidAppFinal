package com.example.appfinal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
fun TelaCadastroUsuario(onBackNavigate: () -> Unit){
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize(),
            //.background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var usuario by remember { mutableStateOf("") }
        var senha by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

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
        OutlinedTextField( //campo e-mail
            value = email,
            onValueChange = { email = it },
            label = { Text("Endereço de e-mail") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button( //botão cadastrar
            onClick = {
                onBackNavigate()
                //navController.navigateUp()
                /*val i = Intent(ctx, HomeScreen::class.java)
                ctx.startActivity(i)*/
            },
            modifier = Modifier
                //.fillMaxWidth()
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Cadastrar")
        }
    }
}