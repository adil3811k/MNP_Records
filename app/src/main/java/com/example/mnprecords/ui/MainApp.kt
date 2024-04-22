package com.example.mnprecords.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mnprecords.ui.Screen.Detail.DetailScreen
import com.example.mnprecords.ui.Screen.Entry.EntryScreen
import com.example.mnprecords.ui.Screen.Home.HomeScreen
import com.example.mnprecords.ui.Screen.Total.TotalScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.name) {
        composable(route =Screens.Home.name){
            HomeScreen(
                goAddRecode = { navController.navigate(route= Screens.Add.name) },
                onCardClick = {
                             navController.navigate(Screens.Detail.name +"$it")
                },
                onTotalScreen = {navController.navigate(Screens.Total.name)}
                )
        }
        composable(route = Screens.Add.name){
            EntryScreen(
                onBack = {navController.popBackStack()},
                navController = navController
            )
        }
        composable(route = Screens.Total.name){
            TotalScreen(onBack = { navController.popBackStack() })
        }
        composable(route = Screens.Detail.name + "{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
            }
        )){
            val id =it.arguments?.getInt("id",1)
            if (id != null) {
                DetailScreen(
                    id = id.toInt(),
                    onBack = { navController.popBackStack() },
                    navController =navController
                    )
            }
        }

    }
}

enum class Screens{
    Home,
    Add,
    Detail,
    Total,
}