package com.example.wipcantieredigitale
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_compiti.*

/**
 *
 */
  class CompitiFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compiti, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaCompiti.layoutManager = LinearLayoutManager(activity)
        listaCompiti.adapter = CompitiAdapter(DatabaseC.getjobs(), requireContext())


     fabAggiungiCompito.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_compitiFragment_to_aggiungiCompitoFragment)


}}}