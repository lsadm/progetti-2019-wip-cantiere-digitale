package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Utente
import kotlinx.android.synthetic.main.fragment_lavoratore.*

class LavoratoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_lavoratore, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val prova: Utente? = it.getParcelable("scelta")

            //PASSAGGIO INFO LAVORATORE A CHAT FRAGMENT
            val bundleDipendente = it
            btnChat.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_lavoratoreFragment_to_chatFragment,bundleDipendente)
            }

            prova?.let {
                campoNome.text = it.nome
                campoTempo.text = it.tempo
                campoCognome.text=it.cognome
                campoUser.text=it.username
                campoMail.text=it.mail
             }


    listaCompiti.setOnClickListener {
       (activity as MainActivity).setL(prova)

        Navigation.findNavController(it).navigate(R.id.action_lavoratoreFragment_to_compitiFragment)
    }
}}}