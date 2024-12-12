package com.example.sadhumster.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes_from_internet")
data class JokeFromInternet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val setup: String,
    val delivery: String,
    var from: String,
    @ColumnInfo(name = "jokes_from_internet") val cachedAt: Long
)