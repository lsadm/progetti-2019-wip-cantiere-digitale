package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wipcantieredigitale.datamodel.compito
import com.example.wipcantieredigitale.datamodel.lavoratore
import kotlinx.android.synthetic.main.fragment_compito.*
import kotlinx.android.synthetic.main.fragment_lavoratore.*
import kotlinx.android.synthetic.main.riga_compito.*
import kotlinx.android.synthetic.main.riga_compito.idCompito
import kotlinx.android.synthetic.main.riga_compito.idDescrizione

class CompitoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compito, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val prova: compito? = it.getParcelable("compito")
            prova?.let {
                idCompito.text = it.nome
                idDescrizione.text = it.desc

            }
        }
    }
}