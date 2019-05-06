package com.example.wipcantieredigitale.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class login(var username: String="", var password: String="",var mail:String="",var classe:String="",val tempo:String="") : Parcelable

