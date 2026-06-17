package com.example.stockpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaEdicion(
    navController: NavController,
    viewModel: StockViewModel,
    productoId: Int
) {
    // Obtenemos el producto desde el ViewModel usando el id recibido
    // por navegación. Como viewModel.productos es observable, cada vez
    // que actualizarStock() cambie la lista, esta pantalla recompone
    // y "producto" refleja el valor más reciente.
    val producto = viewModel.obtenerProducto(productoId)

    if (producto == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Producto no encontrado")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = producto.nombre,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = producto.descripcion,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Stock actual: ${producto.stockActual}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    // Toda la lógica de modificación vive en el ViewModel
                    viewModel.actualizarStock(producto.id, producto.stockActual - 1)
                },
                enabled = producto.stockActual > 0 // No permitir negativos
            ) {
                Text("- Restar")
            }

            Button(
                onClick = {
                    viewModel.actualizarStock(producto.id, producto.stockActual + 1)
                }
            ) {
                Text("+ Sumar")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // popBackStack() vuelve a la Pantalla 2. Como la lista del
                // ViewModel ya fue modificada (es la fuente única de verdad),
                // la Pantalla 2 se recompone automáticamente con el nuevo stock.
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar y Volver")
        }
    }
}
