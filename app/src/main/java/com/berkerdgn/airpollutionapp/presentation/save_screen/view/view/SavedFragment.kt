package com.berkerdgn.airpollutionapp.presentation.save_screen.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.airpollutionapp.databinding.FragmentSavedBinding
import com.berkerdgn.airpollutionapp.presentation.save_screen.view.SavedStationRecyclerAdapter
import com.berkerdgn.airpollutionapp.presentation.save_screen.view.SavedViewModel


class SavedFragment : Fragment() {
    private var _binding : FragmentSavedBinding ?= null
    private val binding get() = _binding!!

    private var savedStationRecyclerAdapter : SavedStationRecyclerAdapter = SavedStationRecyclerAdapter()

    lateinit var  savedViewModel : SavedViewModel


    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedStation = savedStationRecyclerAdapter.savedStations[layoutPosition]
            savedViewModel.deleteSavedStation(selectedStation)
        }

    }



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


        savedViewModel = ViewModelProvider(requireActivity()).get(SavedViewModel::class.java)
        savedViewModel.getSavedStations()

        binding.savedStationRecyclerView.adapter = savedStationRecyclerAdapter
        binding.savedStationRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.savedStationRecyclerView)

        observeLiveDataForSavedStation()
    }


    private fun observeLiveDataForSavedStation(){
        savedViewModel.state.observe(viewLifecycleOwner, Observer {

            if (it.isLoading == true){
                binding.progressBar2.visibility = View.VISIBLE
                binding.textView3.visibility = View.GONE
            }else{
                if (it.savedStations.isEmpty()){
                    binding.progressBar2.visibility = View.GONE
                    binding.textView3.visibility = View.VISIBLE
                }else{
                    binding.progressBar2.visibility = View.GONE
                    binding.textView3.visibility = View.GONE
                    savedStationRecyclerAdapter.savedStations = it.savedStations
                }


            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}