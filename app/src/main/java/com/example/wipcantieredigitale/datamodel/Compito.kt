package com.example.wipcantieredigitale.datamodel


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Compito(var nome: String="", var descrizione: String="", var done:Boolean=false, var dipendenteMail:String="", var codCompito:String="",var approvato:Boolean=false): Parcelable