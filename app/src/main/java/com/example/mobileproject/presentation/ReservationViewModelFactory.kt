package com.example.mobileproject.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobileproject.data.ReservationDao
import com.example.mobileproject.presentation.ReservationsViewModel

class ReservationViewModelFactory(private val reservationdao: ReservationDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservationsViewModel::class.java)) {
            return ReservationsViewModel(reservationdao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
