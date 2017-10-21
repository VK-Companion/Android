package ru.companion.lionzxy.companion.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserProfile(
        @SerializedName("user_id")
        var id: Int,
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("last_name")
        var lastName: String,
        @SerializedName("token")
        var token: String,
        @SerializedName("photo")
        var photo: String,
        @SerializedName("status")
        var status: String = "") : Parcelable {
    constructor() : this(0, "", "", "", "")

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(firstName)
        writeString(lastName)
        writeString(token)
        writeString(photo)
        writeString(status)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserProfile> = object : Parcelable.Creator<UserProfile> {
            override fun createFromParcel(source: Parcel): UserProfile = UserProfile(source)
            override fun newArray(size: Int): Array<UserProfile?> = arrayOfNulls(size)
        }
    }
}