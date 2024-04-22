@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mnprecords.ui.Screen.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mnprecords.DataLayer.MNPRecode
import com.example.mnprecords.R


@Composable
fun HomeScreen(
    goAddRecode:()->Unit,
    onCardClick:(Int)->Unit,
    onTotalScreen:()->Unit,
    modifier: Modifier =Modifier,
    viewModel: HomeViewModel =viewModel(factory = HomeViewModel.factoRY)
) {
    val list  = viewModel.AllRecodes.collectAsState()
    Scaffold(
        topBar = {
            RecodeTopBar(
                tital = "Recode",
                canBackNAvigate = false,
                onBack = { /*TODO*/ },
                onIcon =  onTotalScreen,
                isHomeSceen = true
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = goAddRecode) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add New recode")
            }
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues)
        ) {
            items(list.value){Recode->
                RecodeCard(mnpRecode = Recode, onLCick = onCardClick, onDelete = viewModel::deleleRecode)
            }
        }
    }
}




@Composable
fun RecodeCard(
    mnpRecode: MNPRecode,
    onLCick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onDelete:(MNPRecode)->Unit
) {
    Card (modifier = modifier
        .padding(5.dp)
        .fillMaxWidth()
        .shadow(5.dp, RoundedCornerShape(10.dp))
        .clickable { onLCick(mnpRecode.id) },
    ){
        Row {
            Text(
                text = mnpRecode.Data,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(0.8f)
            )
            IconButton(onClick = { onDelete(mnpRecode) }, modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delele Recode")
            }

            }
                    Row {
                Text(text = "UI Balace is :-", fontSize = 20.sp,modifier= modifier.padding(start = 10.dp))
                Text(text = mnpRecode.UIBalance.toString(), fontSize = 20.sp,modifier= modifier.padding(bottom = 10.dp, start = 5.dp))
            }
                    Row(
                    modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                val size = 20.sp
                Text(text = "Jio :- ${mnpRecode.jio}", fontSize = size)
                Text(text = "Airtel :- ${mnpRecode.Airtel}", fontSize =size )
                Text(text = "VI :- ${mnpRecode.VI}", fontSize = size)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CarePreiwe() {
    RecodeCard(mnpRecode = MNPRecode("29",0,0,0,0), onLCick = {}, onDelete = {})
}


@ExperimentalMaterial3Api
@Composable
fun RecodeTopBar(
    tital: String,
    canBackNAvigate: Boolean,
    onBack: () -> Unit ={},
    modifier: Modifier = Modifier,
    isHomeSceen:Boolean,
    onIcon:()->Unit
) {
    TopAppBar(
        title = { Text(text = tital)},
        navigationIcon = {
            if (canBackNAvigate){
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Arrow"
                    )
                }
            }
        },
        actions = {
            if (isHomeSceen){
                IconButton(onClick = onIcon) {
                    Icon(
                        painter = painterResource(id = R.drawable.currency_rupee_fill0_wght400_grad0_opsz48) ,
                        contentDescription ="GO to Total Screen" )
                }
            }
        }
    )

}

