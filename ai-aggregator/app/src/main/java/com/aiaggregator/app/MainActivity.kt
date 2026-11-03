package com.aiaggregator.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aiaggregator.app.ui.screens.ChatScreen
import com.aiaggregator.app.ui.screens.HomeScreen
import com.aiaggregator.app.ui.screens.PolicyScreen
import com.aiaggregator.app.ui.screens.ProvidersScreen
import com.aiaggregator.app.ui.screens.SettingsScreen
import com.aiaggregator.app.ui.theme.AIAggregatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val prefs = getSharedPreferences("policy", Context.MODE_PRIVATE)
        val accepted = prefs.getBoolean("accepted", false)
        val start = if (accepted) "home" else "policy"
        setContent {
            AIAggregatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(startDestination = start, onPolicyAccepted = {
                        prefs.edit().putBoolean("accepted", true).apply()
                    })
                }
            }
        }
    }
}

@Composable
fun AppNavHost(startDestination: String, onPolicyAccepted: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("policy") {
            PolicyScreen(onAccepted = {
                onPolicyAccepted()
                navController.navigate("home") { popUpTo("policy") { inclusive = true } }
            })
        }
        composable("home") { HomeScreen(navController) }
        composable("providers") { ProvidersScreen(navController) }
        composable("chat") { ChatScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
