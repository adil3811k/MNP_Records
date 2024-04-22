package com.example.mnprecords.ui.Screen.Entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mnprecords.DataLayer.MNPRecode
import com.example.mnprecords.DataLayer.Repository
import com.example.mnprecords.RecodeApplication
import com.example.mnprecords.ui.Screen.Home.RecodeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EntryViewModel(
    private val repository: Repository
):ViewModel() {
    private  val _uiState:MutableStateFlow<RecodeUIState> = MutableStateFlow(RecodeUIState())
    val uiState = _uiState.asStateFlow()
    fun upDateUi(recodeUIState: RecodeUIState){
        _uiState.update {
            recodeUIState
        }
    }
     fun onSummit(recodeUIState: RecodeUIState){
        viewModelScope.launch {
        repository.AddRecode(recodeUIState.toMNPRecode())
        }
    }


    companion object{
        val factory:ViewModelProvider.Factory= viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecodeApplication)
                EntryViewModel(application.container.repository)
            }
        }
    }
}
fun RecodeUIState.toMNPRecode():MNPRecode{
    return MNPRecode(
        Data=this.data,
        jio=this.jio,
        VI=this.VI,
        Airtel =this.Airtel,
        UIBalance = this.ULBalance.toInt()
    )
}