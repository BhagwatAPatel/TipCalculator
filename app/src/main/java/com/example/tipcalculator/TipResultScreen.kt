package com.example.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TipResultScreen(
    billAmount: Float,
    people: Int,
    tipPercent: Int,
    onPickPercentClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val total = billAmount * (1f + tipPercent / 100f)
    val perPerson = total / people
    Column {
        Text("Tip: $tipPercent%")
        Text("Total with tip: \$${"%.2f".format(total)}")
        Text("Each person owes: \$${"%.2f".format(perPerson)}")
        Button(onClick = onPickPercentClick) {
            Text("Choose tip %")
        }
        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}