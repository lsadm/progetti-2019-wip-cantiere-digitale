package com.example.wipcantieredigitale


import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.riga_lavoratore.view.*

class LavoratoreRiga(view: View) : RecyclerView.ViewHolder(view) {
    val tvNome = view.textNome
    val tvDati = view.Dati
}