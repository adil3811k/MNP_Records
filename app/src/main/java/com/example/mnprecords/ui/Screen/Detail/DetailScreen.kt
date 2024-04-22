package com.example.mnprecords.ui.Screen.Detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mnprecords.ui.Screen.Entry.EntaryBody
import com.example.mnprecords.ui.Screen.Home.RecodeTopBar
import com.example.mnprecords.ui.Screen.Home.RecodeUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int,
    onBack:()->Unit,
    viewModel: DetailViewModel = viewModel(factory = DetailViewModel.factory),
    navController: NavController,
) {
    viewModel.getRecode(id)
val UIState = viewModel.UIState.collectAsState()
    Scaffold (
        topBar = {RecodeTopBar(
            tital = "Detail",
            canBackNAvigate = true,
            onBack = onBack,
            isHomeSceen = false,
            onIcon = {}
        )}
    ){paddingValues ->
        DetailBody(
            recodeUIState = UIState.value,
            onvaluecnahe = viewModel::updateUI,
            modifier = Modifier,
            contentPadding = paddingValues,
            onSummit = {
                viewModel.Summit()
                navController.popBackStack()
            }
        )
    }
}
@Composable
fun DetailBody(
    recodeUIState: RecodeUIState,
    onvaluecnahe:(RecodeUIState)->Unit,
    modifier: Modifier= Modifier,
    contentPadding : PaddingValues = PaddingValues(0.dp),
    onSummit: () -> Unit,
) {

    Column (
        modifier= modifier.padding(contentPadding)
    ){
        OutlinedTextField(
            value = recodeUIState.data,
            onValueChange ={onvaluecnahe(recodeUIState.copy(data = it))},
            placeholder = { Text(text = "Enter Data") },
            modifier=modifier
                .padding(10.dp)
                .fillMaxWidth()
            ,
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
        Row (modifier=modifier.padding(10.dp)){
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
        Row (modifier=modifier.padding(10.dp)){
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
        Row (modifier=modifier.padding(10.dp)){
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
        Button(onClick = onSummit,modifier=modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            Text(text = "Summit")
        }
    }

}

