package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
               Surface(modifier = Modifier.fillMaxSize(),
                   color = MaterialTheme.colorScheme.background)
               {
                UnitConverterPreview()

               }

            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor= remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor= remember { mutableDoubleStateOf(1.00) }

    fun convertUnit(){
        val inputValueDouble=inputValue.toDoubleOrNull() ?:0.0
        val result=(inputValueDouble * conversionFactor.doubleValue*100 / oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue=result.toString()
    }



    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = inputValue,
            onValueChange ={inputValue = it
                convertUnit()},
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Box {
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        iExpanded=false
                        inputUnit="Centimeter"
                        conversionFactor.doubleValue=0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        iExpanded=false
                        inputUnit="Meter"
                        conversionFactor.doubleValue=1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded=false
                        inputUnit="Feet"
                        conversionFactor.doubleValue=0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        iExpanded=false
                        inputUnit="Millimeter"
                        conversionFactor.doubleValue=0.001
                        convertUnit()
                    })

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                val context= LocalContext.current
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        oExpanded=false
                        outputUnit="Centimeter"
                        oConversionFactor.doubleValue=0.01
                        convertUnit()
                        Toast.makeText(context,"thanks for using unit calculator",Toast.LENGTH_SHORT).show()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        oExpanded=false
                        outputUnit="Meter"
                        oConversionFactor.doubleValue=1.00
                        convertUnit()
                        Toast.makeText(context,"thanks for using unit calculator",Toast.LENGTH_SHORT).show()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded=false
                        outputUnit="Feet"
                        oConversionFactor.doubleValue=0.3048
                        convertUnit()
                        Toast.makeText(context,"thanks for using unit calculator",Toast.LENGTH_SHORT).show()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        oExpanded=false
                        outputUnit="Millimeter"
                        oConversionFactor.doubleValue=0.001
                        convertUnit()
                        Toast.makeText(context,"thanks for using unit calculator",Toast.LENGTH_SHORT).show()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Result $outputUnit $outputValue")
    }
}


@Preview
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}