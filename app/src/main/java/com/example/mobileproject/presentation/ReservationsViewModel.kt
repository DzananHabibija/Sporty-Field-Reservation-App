package com.example.mobileproject.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileproject.data.Reservation
import com.example.mobileproject.data.ReservationDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReservationsViewModel( private val reservationdao: ReservationDao) : ViewModel() {
    fun reserve(
        title: String,
        sport: String,
        city: String,
        price: String,
        userid: Int,
        date: String,
        hours: String,
        time: String
    ) {
        val reservation = Reservation(
            title = title,
            sport = sport,
            city = city,
            price = price,
            userid = userid,
            date = date,
            hours = hours,
            time = time
        )
        viewModelScope.launch {
            reservationdao.insert(reservation)
        }
    }

    private val _reservations = MutableStateFlow<List<Reservation>>(emptyList())
    val reservations = _reservations.asStateFlow()

    fun getReservationsById(userId: Int) {
        viewModelScope.launch {
            reservationdao.getReservationsId(userId)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
                .collect { reservations ->
                    _reservations.value = reservations
                }
        }
    }

}
