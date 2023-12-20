package com.example.unitconverter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember{ mutableStateOf(false) }
    var inputUnit by remember{ mutableStateOf("Centimeter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    val kTextStyleLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,

        color = Color.Blue
    )
    val kTextStyleMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Blue

    )


    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text("Unit Converter", style = kTextStyleLarge)
        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
         inputValue = it
            convertUnits()
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
     Box {
         Button(onClick = { iExpand = true }) {
             Text(text = inputUnit)
             Icon(
                 Icons.Default.ArrowDropDown, contentDescription = "Arrow Down"
             )
         }
         DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false}) {
             DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                 iExpand = false;
                 inputUnit = "Centimeter";
                 conversionFactor.value = 0.01;
                 convertUnits()
             })
             DropdownMenuItem(text = { Text("Meter") }, onClick = {
                 iExpand = false;
                 inputUnit = "Meter";
                 conversionFactor.value = 1.0;
                 convertUnits()
             })
             DropdownMenuItem(text = { Text("Feet") }, onClick = {
                 iExpand = false;
                 inputUnit = "Feet";
                 conversionFactor.value = 0.3048;
                 convertUnits() })
             DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                 iExpand = false;
                 inputUnit = "Millimeter";
                 conversionFactor.value = 0.001;
                 convertUnits() })
         }
     }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(
                    onClick = {
                    oExpand = true
                }
                )
                {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown, contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand  = false}) {
                    DropdownMenuItem(text = { Text("Centimeter") }, onClick = {
                        oExpand = false;
                        outputUnit = "Centimeter";
                        oConversionFactor.value = 0.01;
                        convertUnits()})
                    DropdownMenuItem(text = { Text("Meter") }, onClick = {
                        oExpand = false;
                        outputUnit = "Meter";
                        oConversionFactor.value = 1.0;
                        convertUnits() })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        oExpand = false;
                        outputUnit = "Feet";
                        oConversionFactor.value = 0.3048;
                        convertUnits() })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        oExpand = false;
                        outputUnit = "Millimeter";
                        oConversionFactor.value = 0.001;
                        convertUnits() })
                }
            }
        }
        Text("Result : $outputValue $outputUnit", style = kTextStyleMedium )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}