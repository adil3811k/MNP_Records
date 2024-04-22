package com.example.mnprecords.ui.Screen.Entry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mnprecords.ui.Screen.Home.RecodeTopBar
import com.example.mnprecords.ui.Screen.Home.RecodeUIState
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun EntryScreen(
    onBack:()->Unit,
    navController: NavController,
    viewModel: EntryViewModel =viewModel(factory = EntryViewModel.factory),
) {
    val StateUI = viewModel.uiState.collectAsState()
    Scaffold (
        topBar = {
            RecodeTopBar(
                tital = "Add Recode",
                canBackNAvigate = true,
                onIcon = {},
                onBack = onBack,
                isHomeSceen = false
            )
        }
    ){paddingValues ->
        EntaryBody(
            recodeUIState = StateUI.value,
            onvaluecnahe = viewModel::upDateUi,
            contentPadding = paddingValues,
            onSummit = {
                viewModel.onSummit(StateUI.value)
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun EntaryBody(
    recodeUIState: RecodeUIState,
    onvaluecnahe:(RecodeUIState)->Unit,
    modifier: Modifier= Modifier,
    contentPadding :PaddingValues = PaddingValues(0.dp),
    onSummit: () -> Unit,
) {
    Column (
        modifier= modifier.padding(contentPadding)
    ){
        OutlinedTextField(
            value = recodeUIState.data,
            onValueChange ={onvaluecnahe(recodeUIState.copy(data = it))},
            placeholder = { Text(text = "Enter Date") },
            modifier= modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = recodeUIState.ULBalance,
            onValueChange ={onvaluecnahe(recodeUIState.copy(ULBalance = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Enter UL Balance Amount")},
            modifier= modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row (modifier = modifier.padding(10.dp)){
            Text(text = "VI :", fontSize = 30.sp,modifier=modifier.fillMaxWidth(0.22f))
            Card {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Icremnent",
                    modifier = modifier
                        .clickable { onvaluecnahe(recodeUIState.copy(VI = recodeUIState.VI + 1)) }
                        .size(40.dp)
                )
            }
            Text(text = recodeUIState.VI.toString(), fontSize = 30.sp)
            Card {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "decriment",
                    modifier = modifier
                        .clickable { onvaluecnahe(recodeUIState.copy(VI = recodeUIState.VI - 1)) }
                        .size(40.dp)
                )
            }
        }
        Row (modifier = modifier.padding(10.dp)){
            Text(text = "Jio :", fontSize = 30.sp,modifier=modifier.fillMaxWidth(0.22f))
            Card {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Icremnent",
                    modifier = modifier
                        .clickable { onvaluecnahe(recodeUIState.copy(jio = recodeUIState.jio + 1)) }
                        .size(40.dp)
                )
            }
            Text(text = recodeUIState.jio.toString(), fontSize = 30.sp)
            Card {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "decriment",
                    modifier = modifier
                        .clickable { onvaluecnahe(recodeUIState.copy(jio = recodeUIState.jio - 1)) }
                        .size(40.dp)
                )
            }
        }
        Row (modifier = modifier.padding(10.dp)){
            Text(text = "Airtel :", fontSize = 30.sp)
            Card {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Icremnent",
                    modifier = modifier
                        .clickable { onvaluecnahe(recodeUIState.copy(Airtel = recodeUIState.Airtel + 1)) }
                        .size(40.dp)
                )
            }
            Text(text = recodeUIState.Airtel.toString(), fontSize = 30.sp)
            Card {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "decriment",
                    modifier = modifier
                        .clickable { onvaluecnahe(recodeUIState.copy(Airtel = recodeUIState.Airtel - 1)) }
                        .size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = onSummit,modifier=modifier.padding(10.dp)
            .fillMaxWidth()) {
            Text(text = "Summit")
        }
    }
}

