package com.example.stockpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.navigation.Rutas
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaIngreso(
    navController: NavController,
    viewModel: StockViewModel
) {
    // Estado local solo para el texto del campo (no necesita estar en el ViewModel
    // porque es temporal y solo pertenece a esta pantalla)
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a StockPro",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del operario") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Guardamos el nombre en el ViewModel para que esté
                // disponible en cualquier pantalla
                viewModel.nombreOperario = nombre
                navController.navigate(Rutas.catalogoConNombre(nombre))
            },
            enabled = nombre.length >= 3, // Habilitado solo si tiene 3+ caracteres
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar al Sistema")
        }
    }
}
