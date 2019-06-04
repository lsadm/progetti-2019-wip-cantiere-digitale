package com.example.wipcantieredigitale


import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.riga_compito.view.*

class CompitoRiga(view: View) : RecyclerView.ViewHolder(view) {
    val nome = view.tvNomeCompito
    val descrizione = view.tvDescrizionee
}