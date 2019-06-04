package com.example.wipcantieredigitale.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Utente(var nome: String="", var cognome: String="", var cellulare:String="", var mail:String="", var password:String="", var ruolo:String="", val tempo:String="") : Parcelable

