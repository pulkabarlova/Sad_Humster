package com.example.sadhumster.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.util.recursiveFetchLongSparseArray

@Entity(tableName = "jokes")
data class Joke(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val setup: String,
    val delivery: String,
    val from: String,
    var favourite: Int=0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(category)
        parcel.writeString(setup)
        parcel.writeString(delivery)
        parcel.writeString(from)
        parcel.writeInt(favourite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Joke> {
        override fun createFromParcel(parcel: Parcel): Joke {
            return Joke(parcel)
        }

        override fun newArray(size: Int): Array<Joke?> {
            return arrayOfNulls(size)
        }
    }
}




