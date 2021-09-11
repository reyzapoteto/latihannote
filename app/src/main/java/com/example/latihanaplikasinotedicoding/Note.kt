package com.example.latihanaplikasinotedicoding

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(

    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var dat: String? = null
) : Parcelable