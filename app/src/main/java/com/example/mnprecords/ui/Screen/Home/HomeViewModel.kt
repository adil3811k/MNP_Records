package com.example.mnprecords.ui.Screen.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mnprecords.DataLayer.MNPRecode
import com.example.mnprecords.DataLayer.Repository
import com.example.mnprecords.RecodeApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
):ViewModel() {
    val AllRecodes = repository.getAllStrem().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    fun deleleRecode(mnpRecode: MNPRecode){
        viewModelScope.launch {
            repository.deleteRecode(mnpRecode)
        }
    }
    companion object{
        val factoRY:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this [APPLICATION_KEY] as RecodeApplication)
                HomeViewModel(application.container.repository)
            }
        }
    }
}

data class RecodeUIState(
    val data:String = "",
    val jio :Int= 0,
    var VI:Int = 0,
    val Airtel:Int = 0,
    val ULBalance:String ="0",
    var isValid:Boolean = false,
    val id:Int = 1
)