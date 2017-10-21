package ru.companion.lionzxy.companion.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DialogModel(
        @SerializedName("peer_id")
        var id: Int,
        @SerializedName("text")
        var messsage: String,
        @SerializedName("user_peer")
        var user: DialogUser) : Parcelable {
    constructor() : this(0, "", DialogUser())

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readParcelable<DialogUser>(DialogUser::class.java.classLoader)
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