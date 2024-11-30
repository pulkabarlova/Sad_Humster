package com.example.sadhumster.model

import android.os.Parcel
import android.os.Parcelable

data class JokeResponse(
    val error: Boolean,
    val amount: Int,
    val jokes: List<Joke>
)

data class Joke(
    val category: String,
    val setup: String,
    val delivery: String,
    val from: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
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
