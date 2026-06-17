package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockpro.ui.screens.PantallaCatalogo
import com.example.stockpro.ui.screens.PantallaEdicion
import com.example.stockpro.ui.screens.PantallaIngreso
import com.example.stockpro.ui.screens.PantallaReporte
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun StockProNavHost(viewModel: StockViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.INGRESO
    ) {
        composable(Rutas.INGRESO) {
            PantallaIngreso(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Rutas.CATALOGO,
            arguments = listOf(navArgument("nombreOperario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombreOperario") ?: ""
            PantallaCatalogo(
                navController = navController,
                viewModel = viewModel,
                nombreOperario = nombre
            )
        }

        composable(
            route = Rutas.EDICION,
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("productoId") ?: -1
            PantallaEdicion(
                navController = navController,
                viewModel = viewModel,
                productoId = id
            )
        }

        composable(Rutas.REPORTE) {
            PantallaReporte(navController = navController, viewModel = viewModel)
        }
    }
}
