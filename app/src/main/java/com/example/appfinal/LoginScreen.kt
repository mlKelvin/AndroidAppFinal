package com.example.appfinal

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onNavigateHome: () -> Unit,
                onNavigateCadastroUsuario: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
            //.background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val ctx = LocalContext.current
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
                onNavigateHome()
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
/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
   // LoginScreen(onNavigateHome = { /*TODO*/ }) {
        
   // }
}*/