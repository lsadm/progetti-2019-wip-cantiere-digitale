package com.example.wipcantieredigitale.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chat (var mittente: String="", var messaggio:String="") : Parcelable
