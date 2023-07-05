package com.berkerdgn.airpollutionapp.presentation.list_screen.view.view

import android.app.DatePickerDialog
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.databinding.FragmentListBinding
import com.berkerdgn.airpollutionapp.presentation.list_screen.view.StationRecyclerAdapter
import com.berkerdgn.airpollutionapp.presentation.list_screen.view.StationsListViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ListFragment() : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var stationRecyclerAdapter: StationRecyclerAdapter = StationRecyclerAdapter()

    lateinit var viewModel: StationsListViewModel


    var startedDate = "11.06.2020 00:00:00"
    var endedDate = "12.06.2020 00:00:00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //DatePicker
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        binding.startDateTextView.setOnClickListener {
            DatePickerDialog(
                this.requireContext(),
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val myCalendar2 = Calendar.getInstance()
        val datePicker2 = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar2.set(Calendar.YEAR, year)
            myCalendar2.set(Calendar.MONTH, month)
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable2(myCalendar2)
        }
        binding.endDateTextView.setOnClickListener {
            DatePickerDialog(
                this.requireContext(),
                datePicker2,
                myCalendar2.get(Calendar.YEAR),
                myCalendar2.get(Calendar.MONTH),
                myCalendar2.get(Calendar.DAY_OF_MONTH)
            ).show()
        }



        viewModel = ViewModelProvider(requireActivity()).get(StationsListViewModel::class.java)


        binding.stationsRecyclerView.adapter = stationRecyclerAdapter
        binding.stationsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        observeLiveDataForStations()
    }

    private fun observeLiveDataForStations() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it.isLoading == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else if (it.stations.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                stationRecyclerAdapter.stations = it.stations

            }
        })

    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.startDateTextView.setText(sdf.format(myCalendar.time))
        startedDate = sdf.format(myCalendar.time) +" 00:00:00"
        stationRecyclerAdapter.startedDate=startedDate
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.endDateTextView.setText(sdf.format(myCalendar.time))
        endedDate = sdf.format(myCalendar.time) +" 00:00:00"
        stationRecyclerAdapter.endedDate=endedDate
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}