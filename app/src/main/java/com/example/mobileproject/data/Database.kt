package com.example.mobileproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Field::class, Reservation::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class SportifyDatabase: RoomDatabase() {
    abstract val dao: FieldDao
    abstract val reservationdao: ReservationDao
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: SportifyDatabase? = null

        fun getDatabase(context: Context): SportifyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportifyDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}