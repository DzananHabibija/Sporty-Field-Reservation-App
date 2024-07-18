package com.example.mobileproject.presentation.uiElements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun textfield(
    placeholderText: String,
    fieldToUpdate: MutableState<String>,
    suffix: String,
    keyboardType: KeyboardType
) {
    var isError by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = fieldToUpdate.value,
        onValueChange = {
            fieldToUpdate.value = it
        },
        textStyle = TextStyle(
            fontSize = 17.sp
        ),
        placeholder = {
            Text(text = placeholderText)
        },
        suffix = { Text(text = suffix) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}