package com.example.stockpro.navigation

object Rutas {
    const val INGRESO = "ingreso"
    const val CATALOGO = "catalogo/{nombreOperario}"
    const val EDICION = "edicion/{productoId}"
    const val REPORTE = "reporte"

    // Funciones helper para construir las rutas con argumentos reales
    fun catalogoConNombre(nombre: String) = "catalogo/$nombre"
    fun edicionConId(id: Int) = "edicion/$id"
}
