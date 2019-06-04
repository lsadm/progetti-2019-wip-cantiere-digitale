package com.example.wipcantieredigitale


 import android.content.Context
import android.os.Bundle
 import android.support.v7.widget.RecyclerView
 import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
 import com.example.wipcantieredigitale.datamodel.Utente


class CapoAdapter(val dataset: ArrayList<Utente?>, val context: Context) : RecyclerView.Adapter<DipendenteRiga>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DipendenteRiga {
        return DipendenteRiga(LayoutInflater.from(context).inflate(R.layout.riga_dipendente, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: DipendenteRiga, position: Int) {
          val dipendente = dataset.get(position)

        viewHolder.NomeCognome.text = (dipendente?.nome+" "+dipendente?.cognome)
        viewHolder.Mail.text = dipendente?.mail

        viewHolder.itemView.setOnClickListener {

            val dipendenteBundle = Bundle()
            dipendenteBundle.putParcelable("dipendente scelto", dipendente)
            Navigation.findNavController(it).navigate(R.id.action_capoFragment_to_dipendenteFragment, dipendenteBundle)

        } }}