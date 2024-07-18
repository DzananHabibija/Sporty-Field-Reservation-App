package com.example.mobileproject.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.mobileproject.data.Field

data class FieldState(
    val fields: List<Field> = emptyList(),

    val field: Field? = null,

    val id: MutableState<Int> = mutableIntStateOf(0),
    val title: MutableState<String> = mutableStateOf(""),
    val sport: MutableState<String> = mutableStateOf(""),
    val city: MutableState<String> = mutableStateOf(""),
    val price: MutableState<String> = mutableStateOf(""),
    val time: MutableState<String> = mutableStateOf("")
)