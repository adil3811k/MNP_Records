package com.example.mnprecords.ui.Screen.Total

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mnprecords.DataLayer.Repository
import com.example.mnprecords.RecodeApplication
import kotlinx.coroutines.flow.Flow

class TotalViewModel(
    private val repository: Repository
) :ViewModel(){
    val vi:Flow<Int> = repository.getViTotal()
    val Jio:Flow<Int> = repository.getJioTotal()
    val Airtel:Flow<Int> = repository.getAirtel()
    val Balance:Flow<Int> = repository.getTotalUIBalance()



companion object{
    val factory:ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val appliation = (this[APPLICATION_KEY] as RecodeApplication)
            TotalViewModel(appliation.container.repository)
        }
    }
}
}