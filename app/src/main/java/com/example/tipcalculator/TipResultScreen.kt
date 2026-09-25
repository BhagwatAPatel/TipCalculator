package com.example.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TipResultScreen(
    billAmount: Float,
    people: Int,
    onBackClick: () -> Unit
) {
    val tipPercent = 15
    val total = billAmount * (1f + tipPercent / 100f)
    val perPerson = total / people
    Column {
        Text("Total with tip: \$${"%.2f".format(total)}")
        Text("Each person owes: \$${"%.2f".format(perPerson)}")
        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}