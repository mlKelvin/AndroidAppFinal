package com.example.appfinal

import android.content.Intent
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onNavigateHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
            //.background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text(
            text = "Login",
        )

        Button(onClick = {
            onNavigateHome()
        }) {
            Text(text = "Navigate to Home")
        }*/
        val ctx = LocalContext.current
        var usuario by remember { mutableStateOf("") }
        var senha by remember { mutableStateOf("") }
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuário") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
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
    }
}