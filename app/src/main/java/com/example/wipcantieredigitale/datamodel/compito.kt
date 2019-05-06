package com.example.wipcantieredigitale.datamodel


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class compito(var nome: String="", var desc: String="",var done:Boolean=false) : Parcelable