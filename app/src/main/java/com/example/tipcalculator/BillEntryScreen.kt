package com.example.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BillEntryScreen(onCalculateClick: (Float, Int) -> Unit) {
    var billAmountText by remember { mutableStateOf("") }
    var peopleCountText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    Column {
        TextField(
            value = billAmountText,
            onValueChange = { billAmountText = it },
            label = { Text("Bill amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        TextField(
            value = peopleCountText,
            onValueChange = { peopleCountText = it },
            label = { Text("Number of people") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (errorMessage != null) {
            Text(errorMessage!!)
        }

        Button(onClick = {
            val billAmount = billAmountText.toFloatOrNull()
            val people = peopleCountText.toIntOrNull()
            when {
                billAmount == null || billAmount < 0f ->
                    errorMessage = "Enter a valid bill amount"
                people == null || people <= 0 ->
                    errorMessage = "Enter at least 1 person"
                else -> {
                    errorMessage = null
                    onCalculateClick(billAmount, people)
                }
            }
        }) {
            Text("Calculate")
        }
    }
}