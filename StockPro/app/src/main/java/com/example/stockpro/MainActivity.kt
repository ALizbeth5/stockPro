package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.stockpro.navigation.StockProNavHost
import com.example.stockpro.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {

    // El ViewModel se crea una sola vez y sobrevive a cambios de
    // configuración (ej. rotación de pantalla). Es la única fuente
    // de verdad compartida entre las 4 pantallas.
    private val stockViewModel: StockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StockProNavHost(viewModel = stockViewModel)
                }
            }
        }
    }
}
