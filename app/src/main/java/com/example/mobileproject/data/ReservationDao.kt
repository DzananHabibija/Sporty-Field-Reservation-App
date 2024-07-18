package com.example.mobileproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {

    @Upsert
    suspend fun upsertField(reservation: Reservation)

    @Insert
    suspend fun insert(reservation: Reservation)

    @Delete
    suspend fun deleteField(reservation: Reservation)

    @Query("SELECT * FROM reservation WHERE userid = :userid")
    fun getReservationsId(userid: Int): Flow<List<Reservation>>
}