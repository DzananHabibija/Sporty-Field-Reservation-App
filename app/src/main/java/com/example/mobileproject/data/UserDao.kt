package com.example.mobileproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmail(email: String,password: String): User?

    @Query("SELECT id FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserId(email: String): Int
}
