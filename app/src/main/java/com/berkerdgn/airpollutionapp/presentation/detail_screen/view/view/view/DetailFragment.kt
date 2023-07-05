package com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view.view

import android.os.Bundle
import android.text.SpannableString
import android.text.style.LineHeightSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.databinding.FragmentDetailBinding
import com.berkerdgn.airpollutionapp.domain.use_case.saved_screen_use_case.SavedStationUseCase
import com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view.DetailRecyclerAdapter
import com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view.DetailViewModel
import com.berkerdgn.airpollutionapp.presentation.save_screen.view.SavedViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    lateinit var viewModel :  DetailViewModel
    private var detailRecyclerAdapter: DetailRecyclerAdapter = DetailRecyclerAdapter()

    lateinit var savedViewModel : SavedViewModel

    lateinit var name :String
    lateinit var startDate :String
    lateinit var endDate :String
    lateinit var stationId :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            name = DetailFragmentArgs.fromBundle(it).stationName
            startDate = DetailFragmentArgs.fromBundle(it).startDate
            endDate = DetailFragmentArgs.fromBundle(it).endDate
            stationId = DetailFragmentArgs.fromBundle(it).stationId

        }
        //for underline the station name
        val stationName = binding.stationNameTextView
        val mySpannableString = SpannableString(name)
        mySpannableString.setSpan(UnderlineSpan(), 0, mySpannableString.length, 0)
        stationName.text = mySpannableString

        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
        viewModel.getDetailAccessibleAll(stationId = stationId, startDate = startDate, endDate = endDate)

        binding.detailRecyclerView.adapter = detailRecyclerAdapter
        binding.detailRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        observeLiveDataForDetails()

        binding.savefloatingActionButton.setOnClickListener {
            savedViewModel = ViewModelProvider(requireActivity()).get(SavedViewModel::class.java)
            savedViewModel.saveStation(stationID = stationId, startDate = startDate, stationName = name, endDate = endDate)
            Toast.makeText(context,"Successfully saved!", Toast.LENGTH_LONG).show()
        }

    }

    private fun observeLiveDataForDetails(){
        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it.isLoading == true){
                println("error")
            }else{
                detailRecyclerAdapter.details = it.stationDetails
            }
        })

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}