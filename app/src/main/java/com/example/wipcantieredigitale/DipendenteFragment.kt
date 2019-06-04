package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.Utente
import kotlinx.android.synthetic.main.fragment_dipendente.*

class DipendenteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_dipendente, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val dipendente: Utente? = it.getParcelable("dipendente scelto")

            /*//PASSAGGIO INFO LAVORATORE A CHAT FRAGMENT
            val bundleDipendente = it
            btnChat.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_dipendenteFragment_to_chatFragment,bundleDipendente)
            }*/

            dipendente?.let {
                campoNome.text = it.nome
                campoCognome.text=it.cognome
                campoCellulare.text=it.cellulare
                campoMail.text=it.mail
                campoTempo.text = it.tempo
            }

            btnCompiti.setOnClickListener {
                //(activity as MainActivity).setL(prova)  da cancellare????????
                val dipendenteBundle = Bundle()
                dipendenteBundle.putParcelable("dipendente scelto", dipendente)
                Navigation.findNavController(it).navigate(R.id.action_dipendenteFragment_to_compitiFragment,dipendenteBundle)
            }
        }
    }
}