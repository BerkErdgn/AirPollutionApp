package com.berkerdgn.airpollutionapp.presentation.save_screen.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.databinding.FragmentSavedBinding
import com.berkerdgn.airpollutionapp.presentation.save_screen.view.SavedStationRecyclerView
import com.berkerdgn.airpollutionapp.presentation.save_screen.view.SavedViewModel


class SavedFragment : Fragment() {
    private var _binding : FragmentSavedBinding ?= null
    private val binding get() = _binding!!

    private val savedStationRecyclerAdapter : SavedStationRecyclerView = SavedStationRecyclerView()

    lateinit var  savedViewModel : SavedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSavedBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savedStationRecyclerView.adapter = savedStationRecyclerAdapter
        binding.savedStationRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        savedViewModel = ViewModelProvider(requireActivity()).get(SavedViewModel::class.java)

        observeLiveDataForSavedStation()
    }


    private fun observeLiveDataForSavedStation(){
        savedViewModel.state.observe(viewLifecycleOwner, Observer {
            if (it.savedStations.isNotEmpty()){
                savedStationRecyclerAdapter.savedStations = it.savedStations
            }else{
                println("problem")
            }
        })
    }

}