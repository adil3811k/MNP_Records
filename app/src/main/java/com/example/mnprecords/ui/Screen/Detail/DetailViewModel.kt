package com.example.mnprecords.ui.Screen.Detail

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mnprecords.DataLayer.MNPRecode
import com.example.mnprecords.DataLayer.Repository
import com.example.mnprecords.RecodeApplication
import com.example.mnprecords.ui.Screen.Entry.toMNPRecode
import com.example.mnprecords.ui.Screen.Home.RecodeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
   private val repository: Repository
):ViewModel() {
    private val _UIState = MutableStateFlow(RecodeUIState())
    var UIState = _UIState.asStateFlow()
    fun getRecode(id:Int){
        viewModelScope.launch {
            val  data =repository.selectcteRecode(id)
            _UIState.value = data.toRecodeUIState()
            }
        }
    fun updateUI(recodeUIState: RecodeUIState){
        _UIState.update {
            recodeUIState
        }
    }
    fun Summit(){
        viewModelScope.launch {
            repository.AddRecode(_UIState.value.toMNPRecode())
        }
    }


    companion object{
        val factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecodeApplication)
                DetailViewModel(application.container.repository)
            }
        }
    }
}
fun MNPRecode.toRecodeUIState():RecodeUIState{
    return RecodeUIState(
        this.Data,
        this.jio,
        this.VI,
        this.Airtel,
        this.UIBalance.toString(),
        id = this.id
    )
}