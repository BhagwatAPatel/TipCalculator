package com.example.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TippingGuidScreen(
    onBackClick: () -> Unit) {
    Column {
        Text("15% is standard, 20% for excellent service.")

        Button(onClick = onBackClick) {
            Text("Back")
        }

    }
}