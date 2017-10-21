package ru.companion.lionzxy.companion.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DialogUser(
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("last_name")
        var lastName: String,
        @SerializedName("photo")
        var photo: String) : Parcelable {
    constructor() : this("", "", "")

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(firstName)
        writeString(lastName)
        writeString(photo)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DialogUser> = object : Parcelable.Creator<DialogUser> {
            override fun createFromParcel(source: Parcel): DialogUser = DialogUser(source)
            override fun newArray(size: Int): Array<DialogUser?> = arrayOfNulls(size)
        }
    }
}