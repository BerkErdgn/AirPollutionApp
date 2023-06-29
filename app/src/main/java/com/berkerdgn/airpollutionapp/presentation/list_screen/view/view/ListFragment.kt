package com.berkerdgn.airpollutionapp.presentation.list_screen.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.databinding.FragmentListBinding
import com.berkerdgn.airpollutionapp.presentation.list_screen.view.StationsListViewModel


class ListFragment() : Fragment() {

    private var _binding : FragmentListBinding?= null
    private val binding get() = _binding!!

    lateinit var  viewModel: StationsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(StationsListViewModel::class.java)

        viewModel.state.observe(viewLifecycleOwner, Observer {
            val deneme = it.stations
            for (station in deneme){
                println(station.Name)
            }
        })

    }




}