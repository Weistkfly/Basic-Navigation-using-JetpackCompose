package com.example.basicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.basicnavigation.ui.theme.BasicNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicNavigationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        Greeting(name = "Weistkfly", navController)
                    }
                    composable(
                        "newHome?name={name}",
                        arguments = listOf(navArgument("name") {
                            type = NavType.StringType
                            defaultValue = "Weistkfly"
                        })
                    ){backStackEntry ->
                        Greeting2(backStackEntry.arguments?.getString("name"))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, navController: NavController) {
    var newName by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name!")
        OutlinedTextField(
            value = newName,
            placeholder = {
                Text(text = "Here goes your names")
            },
            label = {
                    Text( text = "Your name")
            },
            onValueChange = {
                newName = it
            }
        )
        Button(onClick = { navController.navigate("newHome?name={${newName.text}}") }) {
            Text(text = "Set name to ${newName.text} in a new activity")
        }
    }
}

@Composable
fun Greeting2(name: String?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $name again!")
    }
}
