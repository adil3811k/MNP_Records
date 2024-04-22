package com.example.mnprecords.ui.Screen.Total

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mnprecords.ui.Screen.Home.RecodeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalScreen(
    modifier: Modifier = Modifier,
    viewModel: TotalViewModel = viewModel(factory = TotalViewModel.factory),
    onBack:()->Unit,
) {
    val VI= viewModel.vi.collectAsState(initial = 0)
    val Jio = viewModel.Jio.collectAsState(initial = 0)
    val Airtel= viewModel.Airtel.collectAsState(initial = 0)
    val Balance= viewModel.Balance.collectAsState(initial = 0)
    Scaffold (
        topBar = { RecodeTopBar(
            tital = "Total",
            canBackNAvigate = true,
            onIcon = {},
            isHomeSceen = false,
            onBack = onBack,
        ) }
    ){Paddingvalue->
        Column {
            Spacer(modifier = modifier.padding(Paddingvalue))
            Card (
                modifier= modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ){
                val textsize = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)
                Row {
                    Text(text = "VI Total :- ", style = textsize)
                    Text(text = VI.value.toString(),style = textsize)
                }
                Row {
                    Text(text = "Jio Total :- ",style = textsize)
                    Text(text = Jio.value.toString(),style = textsize)
                }
                Row {
                    Text(text = "Airtel Total :- ",style = textsize)
                    Text(text = Airtel.value.toString(),style = textsize)
                }
                Row {
                    Text(text = "Total UL Balance :- ",style = textsize)
                    Text(text = Balance.value.toString(),style = textsize)
                }
            }
        }
    }
}