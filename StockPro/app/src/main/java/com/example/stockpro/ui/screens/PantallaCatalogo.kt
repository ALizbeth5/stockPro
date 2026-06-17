package com.example.stockpro.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.model.Producto
import com.example.stockpro.navigation.Rutas
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaCatalogo(
    navController: NavController,
    viewModel: StockViewModel,
    nombreOperario: String
) {
    // Estado local: controla qué filtro está activo (no es lógica de negocio,
    // solo afecta qué se muestra en esta pantalla)
    var mostrarSoloCriticos by remember { mutableStateOf(false) }

    // La lista que se muestra depende del filtro. Como viewModel.productos
    // es un mutableStateListOf, cualquier cambio hecho en la Pantalla 3
    // se refleja aquí automáticamente al recomponer.
    val listaAMostrar: List<Producto> = if (mostrarSoloCriticos) {
        viewModel.obtenerProductosEnRiesgo()
    } else {
        viewModel.productos
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Rutas.REPORTE) }) {
                Icon(Icons.Filled.Assessment, contentDescription = "Reporte Financiero")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Operario: $nombreOperario",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { mostrarSoloCriticos = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ver Todo")
                }
                Button(
                    onClick = { mostrarSoloCriticos = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Stock Crítico")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaAMostrar, key = { it.id }) { producto ->
                    TarjetaProducto(
                        producto = producto,
                        onClick = {
                            navController.navigate(Rutas.edicionConId(producto.id))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetaProducto(producto: Producto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = producto.nombre, fontWeight = FontWeight.Bold)
                Text(text = "$${"%.2f".format(producto.precio)}")
            }
            Text(
                text = "Stock: ${producto.stockActual}",
                color = if (producto.stockActual < 5) Color.Red else Color.Unspecified,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
