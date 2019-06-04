package com.example.wipcantieredigitale

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wipcantieredigitale.datamodel.Messaggio

class MessaggiAdapter(val dataset: ArrayList<Messaggio?>, val context: Context) : RecyclerView.Adapter<RigaChatViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RigaChatViewHolder {
        return RigaChatViewHolder(LayoutInflater.from(context).inflate(R.layout.riga_chat, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: RigaChatViewHolder, position: Int) {

        val chat = dataset.get(position)
        viewHolder.tvMessaggio.text = chat?.messaggio

    }

}