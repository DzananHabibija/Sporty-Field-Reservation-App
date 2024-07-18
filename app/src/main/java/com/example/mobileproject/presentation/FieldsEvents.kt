package com.example.mobileproject.presentation

import com.example.mobileproject.data.Field

sealed interface FieldsEvent {
    object SortFields: FieldsEvent

    data class DeleteField(val field: Field) : FieldsEvent

    data class SaveField(
        val title: String,
        val sport: String,
        val city: String,
        val price: String,
        val time: String
    ): FieldsEvent

}