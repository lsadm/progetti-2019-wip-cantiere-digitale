package com.example.wipcantieredigitale


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.login


class CapoAdapter(val dataset: ArrayList<login?>, val context: Context) : RecyclerView.Adapter<LavoratoreRiga>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LavoratoreRiga {
        return LavoratoreRiga(LayoutInflater.from(context).inflate(R.layout.riga_lavoratore, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: LavoratoreRiga, position: Int) {

        val worker = dataset.get(position)

        viewHolder.tvNome.text = (worker?.nome+" "+worker?.cognome)
        viewHolder.tvDati.text = worker?.username
        viewHolder.itemView.setOnClickListener {

            val b = Bundle()
            b.putParcelable("scelta", worker)
            Navigation.findNavController(it).navigate(R.id.action_capoFragment_to_lavoratoreFragment, b)
        }
    }

}
