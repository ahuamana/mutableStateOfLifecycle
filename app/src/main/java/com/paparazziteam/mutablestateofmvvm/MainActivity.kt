package com.paparazziteam.mutablestateofmvvm

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paparazziteam.mutablestateofmvvm.ui.theme.MutableStateOfMvvmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MutableStateOfMvvmTheme {
                // A surface container using the 'background' color from the theme
                Home()
            }
        }
    }
}

@Composable
fun Home() {
    val viewModel = hiltViewModel<HomeViewModel>()
    HomeScreen(
        viewModel.nombres,
        viewModel.apellidos,
        viewModel.edad
    )
}


@Composable
fun HomeScreen(
    nombres : MutableState<String>,
    apellidos : MutableState<String>,
    edad : MutableState<String>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SimpleTextField(text = nombres, label = "Nombre", placeholder = "Escriba su nombre")
        Spacer(modifier = Modifier.height(height = 16.dp))
        SimpleTextField(text = apellidos,label = "Apellido", placeholder = "Escriba su apellido")
        Spacer(modifier = Modifier.height(height = 16.dp))
        SimpleTextField(text = edad,label = "Edad", placeholder = "Escriba su edad")
    }
}


@Preview
@Composable
fun HomeScreenPrev() {
    val nombres = remember { mutableStateOf("") }
    val apellidos = remember { mutableStateOf("") }
    val edad = remember { mutableStateOf("") }
    HomeScreen(
        nombres = nombres,
        apellidos = apellidos,
        edad = edad
    )
}


@Preview
@Composable
fun SimpleTextField() {
    val text = remember { mutableStateOf("") }
    SimpleTextField(text  = text ,label = "label", placeholder = "placeholder")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    text : MutableState<String>,
    label : String = "label",
    placeholder : String = "Placeholder",
){
    TextField(
        value = text.value,
        onValueChange = { newtext ->
            text.value  = newtext
        },
        label = { Text(text = label)},
        placeholder = { Text(text = placeholder)}

    )
}



