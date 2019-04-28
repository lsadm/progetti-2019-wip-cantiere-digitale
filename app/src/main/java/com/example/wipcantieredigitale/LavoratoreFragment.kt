package com.example.wipcantieredigitale


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.wipcantieredigitale.datamodel.login
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
            val prova: login? = it.getParcelable("scelta")
            prova?.let {
                campoNome.text = it.username
                campoTempo.text = it.mail
                campoCognome.text="null"
                campoUser.text="null"
                campoCompiti.text="null"
             }
        }

    button.setOnClickListener {

        Navigation.findNavController(it).navigate(R.id.action_lavoratoreFragment_to_compitiFragment)
    }
}}