package com.example.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TipPercentPickerScreen(onPercentChosen: (Int) -> Unit) {
    Column {
        Text("Choose a tip percentage")
        listOf(10, 15, 20).forEach { percent ->
            Button(onClick = { onPercentChosen(percent) }) {
                Text("$percent%")
            }
        }
    }
}