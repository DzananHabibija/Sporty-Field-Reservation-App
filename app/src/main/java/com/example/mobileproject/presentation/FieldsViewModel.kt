package com.example.mobileproject.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileproject.data.Field
import com.example.mobileproject.data.FieldDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FieldsViewModel(
    private val dao: FieldDao
) : ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)

    private var notes =
        isSortedByDateAdded.flatMapLatest { sort ->
            if (sort) {
                dao.getFieldsOrderedByDateAdded()
            } else {
                dao.getFieldsOrderedByTitle()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(FieldState())
    val state =
        combine(_state, isSortedByDateAdded, notes) { state, isSortedByDateAdded, fields ->
            state.copy(
                fields = fields
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FieldState())



    fun onEvent(event: FieldsEvent) {
        when (event) {
            is FieldsEvent.DeleteField -> {
                viewModelScope.launch {
                    dao.deleteField(event.field)
                }
            }

            is FieldsEvent.SaveField -> {
                val note = Field(
                    title = state.value.title.value,
                    dateAdded = System.currentTimeMillis(),
                    sport = state.value.sport.value,
                    city = state.value.city.value,
                    price = state.value.price.value,
                    time = state.value.time.value
                )

                viewModelScope.launch {
                    dao.upsertField(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        sport = mutableStateOf(""),
                        city = mutableStateOf(""),
                        price = mutableStateOf(""),
                        time = mutableStateOf("")
                    )
                }
            }

            FieldsEvent.SortFields -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }

            else -> {
                // Handle unexpected case here
            }

        }
    }

}
