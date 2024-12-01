package com.example.sadhumster.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class JokeResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<Joke>
)
@Entity(tableName = "jokes")
data class Joke(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val setup: String,
    val delivery: String,
    val from: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(category)
        parcel.writeString(setup)
        parcel.writeString(delivery)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Joke> {
        override fun createFromParcel(parcel: Parcel): Joke = Joke(parcel)
        override fun newArray(size: Int): Array<Joke?> = arrayOfNulls(size)
    }
}

@Entity(tableName = "jokes_from_internet")
data class JokeFromInternet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val setup: String,
    val delivery: String,
    val from: String,
    @ColumnInfo(name = "jokes_from_internet") val cachedAt: Long
)




