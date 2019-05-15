package com.example.wipcantieredigitale.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chat (var messaggio:String="") : Parcelable

