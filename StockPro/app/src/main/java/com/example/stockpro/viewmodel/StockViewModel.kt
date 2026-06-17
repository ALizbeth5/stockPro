package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.model.Producto

class StockViewModel : ViewModel() {

    // Lista reactiva: cualquier cambio aquí redibuja automáticamente
    // todas las pantallas que la estén leyendo (Pantalla 2, 3 y 4)
    val productos = mutableStateListOf(
        Producto(1, "Taladro Eléctrico", "Taladro percutor 1/2 pulgada, 750W", 89.99, 12),
        Producto(2, "Caja de Tornillos", "Caja x100 tornillos autorroscantes 1in", 4.50, 3),
        Producto(3, "Martillo de Acero", "Martillo de carpintero cabeza de acero", 15.75, 8),
        Producto(4, "Cinta Métrica", "Cinta métrica 5 metros con bloqueo", 6.25, 2),
        Producto(5, "Guantes de Seguridad", "Guantes antideslizantes talla M", 3.99, 0),
        Producto(6, "Casco de Protección", "Casco ajustable con ventilación", 12.50, 4)
    )

    // Nombre del operario que ingresó en la Pantalla 1
    var nombreOperario: String = ""

    // ---------- FUNCIONES DE NEGOCIO (toda la lógica vive aquí) ----------

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = productos.indexOfFirst { it.id == id }
        if (index != -1) {
            // Como mutableStateListOf es observable, reemplazar el elemento
            // en su índice notifica a Compose que debe recomponer la UI
            // en cualquier pantalla que use esta lista.
            val actualizado = productos[index].copy(stockActual = nuevaCantidad)
            productos[index] = actualizado
        }
    }

    fun calcularValorTotalInventario(): Double {
        return productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return productos.filter { it.stockActual < 5 }
    }

    fun obtenerProductosConStockCero(): Int {
        return productos.count { it.stockActual == 0 }
    }
}
