package com.example.movies.model.dtos

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class MoviesDTO(
    @SerializedName("results") val movies: List<MovieDTO>
)

data class MovieDTO(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDTO> {
        override fun createFromParcel(parcel: Parcel): MovieDTO {
            return MovieDTO(parcel)
        }

        override fun newArray(size: Int): Array<MovieDTO?> {
            return arrayOfNulls(size)
        }
    }
}
