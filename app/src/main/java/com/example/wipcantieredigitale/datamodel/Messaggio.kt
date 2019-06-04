package com.example.wipcantieredigitale.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Messaggio (var messaggio:String="", var mittente: String="", var destinatario: String="") : Parcelable

