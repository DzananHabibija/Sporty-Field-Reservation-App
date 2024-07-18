package com.example.mobileproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDao {

    @Upsert
    suspend fun upsertField(field: Field)

    @Delete
    suspend fun deleteField(field: Field)

    @Query("SELECT * FROM fields ORDER BY dateAdded")
    fun getFieldsOrderedByDateAdded(): Flow<List<Field>>

    @Query("SELECT * FROM fields ORDER BY dateAdded ASC")
    fun getFieldsOrderedByTitle(): Flow<List<Field>>

    @Query("SELECT * FROM fields WHERE id = :id")
    fun getFieldbyId(id: Int): Flow<Field>
}