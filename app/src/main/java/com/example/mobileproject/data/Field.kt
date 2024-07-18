package com.example.mobileproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "fields")
data class Field(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,


    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "dateAdded")
    val dateAdded: Long,

    @ColumnInfo(name = "sport")
    val sport: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "time")
    val time: String
)
