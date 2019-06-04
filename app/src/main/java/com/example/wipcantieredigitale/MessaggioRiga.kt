package com.example.wipcantieredigitale

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.riga_messaggio.view.*

class MessaggioRiga(view: View) : RecyclerView.ViewHolder(view) {
    val tvMessaggio = view.textMessaggio
    val tvMittente=view.textMittente
}