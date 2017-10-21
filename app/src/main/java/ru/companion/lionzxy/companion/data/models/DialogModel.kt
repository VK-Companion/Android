package ru.companion.lionzxy.companion.data.models

import android.os.Parcel
import android.os.Parcelable

data class DialogModel(val id: Int,
                       val messsage: String,
                       val user: UserProfile) : Parcelable {
    constructor() : this(0, "", UserProfile())

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readParcelable<UserProfile>(UserProfile::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(messsage)
        writeParcelable(user, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DialogModel> = object : Parcelable.Creator<DialogModel> {
            override fun createFromParcel(source: Parcel): DialogModel = DialogModel(source)
            override fun newArray(size: Int): Array<DialogModel?> = arrayOfNulls(size)
        }
    }
}