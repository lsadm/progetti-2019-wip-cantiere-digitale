package com.example.wipcantieredigitale

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wipcantieredigitale.datamodel.Messaggio

class MessaggiAdapter(val dataset: ArrayList<Messaggio?>, val context: Context) : RecyclerView.Adapter<MessaggioRiga>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MessaggioRiga {
        return MessaggioRiga(LayoutInflater.from(context).inflate(R.layout.riga_messaggio, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: MessaggioRiga, position: Int) {

        val messaggio = dataset.get(position)
        viewHolder.tvMessaggio.text = messaggio?.testo
        viewHolder.tvMittente.text=messaggio?.mittente
    }

}