package com.example.wipcantieredigitale


import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.riga_dipendente.view.*

class DipendenteRiga(view: View) : RecyclerView.ViewHolder(view) {
    val NomeCognome = view.tvNomeCognome
    val Mail = view.tvMail
}