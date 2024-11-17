package com.example.sadhumster.model

import android.os.Parcel
import android.os.Parcelable

data class Joke(
    val category: String,
    val question: String,
    val answer: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(question)
        parcel.writeString(answer)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Joke> {
        override fun createFromParcel(parcel: Parcel): Joke = Joke(parcel)
        override fun newArray(size: Int): Array<Joke?> = arrayOfNulls(size)
    }
}
