package com.example.wipcantieredigitale


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Compito


class CompitiAdapter(val dataset: ArrayList<Compito?>, val context: Context) : RecyclerView.Adapter<CompitoRiga>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CompitoRiga {
        return CompitoRiga(LayoutInflater.from(context).inflate(R.layout.riga_compito, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(viewHolder: CompitoRiga, position: Int) {
        viewHolder.setIsRecyclable(false)
        val compito = dataset.get(position)

        viewHolder.nome.text = compito?.nome
        viewHolder.descrizione.text = compito?.descrizione
        if(compito!!.done && !compito.approvato)
            viewHolder.nome.setBackgroundColor(Color.GRAY)
        if(compito.approvato)
            viewHolder.nome.setChecked(true)

        viewHolder.itemView.setOnClickListener {
            val compitoBundle = Bundle()
            compitoBundle.putParcelable("compito scelto", compito)
            Navigation.findNavController(it).navigate(R.id.action_compitiFragment_to_compitoFragment, compitoBundle)

        }
    }

}
