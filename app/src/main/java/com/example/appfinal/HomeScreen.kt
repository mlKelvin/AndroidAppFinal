package com.example.appfinal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun HomeScreen(userID: String) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = CustomColors.bottomNavigationColor,
            ) {
                BottomNavigationItem(selected = true,
                    onClick = {
                        navController.navigate("novo/{userID}")
                    },
                    label = {
                        Text(text = "Novo")
                    },
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = "")
                    }
                )

                BottomNavigationItem(selected = false,
                    onClick = {
                        navController.navigate("viagens")
                    },
                    label = {
                        Text(text = "Viagens")
                    },
                    icon = {
                        Icon(Icons.Filled.AccountBox, contentDescription = "")
                    }
                )

                BottomNavigationItem(selected = false,
                    onClick = {
                        navController.navigate("sobre")
                    },
                    label = {
                        Text(text = "Sobre")
                    },
                    icon = {
                        Icon(Icons.Filled.Add, contentDescription = "")
                    }
                )
            }
        }
    ){
        Column() {
            NavHost(navController = navController,
                startDestination = "novo/{userID}",
                modifier = Modifier.padding(paddingValues = it)
            ) {
                composable("novo/{userID}",
                    arguments = listOf(navArgument("userID") { type = NavType.StringType })
                ) {
                    if (userID != null) {
                        TelaNovo(userID)                    }
                }
                composable("viagens"){
                    if (userID != null) {
                        ListaViagens(userID, onNavigateHome = { navController.navigateUp() })
                    }
                }
                composable("sobre"){
                    TelaSobre()
                }
            }
        }

    }
}

object CustomColors {
    val darkYellow = Color(0xFFA56F00)
    val bottomNavigationColor = darkYellow
}