 package com.example.wipcantieredigitale

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class lavoratore(var nome: String, var dati: String) : Parcelable