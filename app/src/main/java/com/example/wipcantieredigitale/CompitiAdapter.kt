package com.example.wipcantieredigitale


import android.content.Context
 import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.compito


class CompitiAdapter(val dataset: ArrayList<compito?>, val context: Context) : RecyclerView.Adapter<CompitoRiga>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CompitoRiga {
        return CompitoRiga(LayoutInflater.from(context).inflate(R.layout.riga_compito, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataset.size

    }

   override fun onBindViewHolder(viewHolder: CompitoRiga, position: Int) {

       val job = dataset.get(position)
         viewHolder.nome.text = job?.nome
        viewHolder.desc.text = job?.desc

        viewHolder.itemView.setOnClickListener {

             val b = Bundle()
            b.putParcelable("compito", job)
            Navigation.findNavController(it).navigate(R.id.action_compitiFragment_to_compitoFragment, b)
        }
    }

}
