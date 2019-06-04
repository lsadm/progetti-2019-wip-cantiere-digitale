package com.example.wipcantieredigitale.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Messaggio (var testo:String="", var mittente: String="", var destinatario: String="") : Parcelable

