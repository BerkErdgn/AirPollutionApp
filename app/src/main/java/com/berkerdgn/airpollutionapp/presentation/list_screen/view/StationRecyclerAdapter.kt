package com.berkerdgn.airpollutionapp.presentation.list_screen.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.data.remote.dto.AllStationsModelItem
import com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view.view.DetailFragmentDirections
import com.berkerdgn.airpollutionapp.presentation.second_stage_screen.view.SecondStageFragmentDirections


class StationRecyclerAdapter(): RecyclerView.Adapter<StationRecyclerAdapter.StationViewHolder>() {

    class StationViewHolder (itemview: View): RecyclerView.ViewHolder(itemview)

    private val diffUtil = object : DiffUtil.ItemCallback<AllStationsModelItem>(){
        override fun areItemsTheSame(
            oldItem: AllStationsModelItem,
            newItem: AllStationsModelItem
        ): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AllStationsModelItem,
            newItem: AllStationsModelItem
        ): Boolean {
            return  oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var stations: List<AllStationsModelItem>
        get() = recyclerListDiffer.currentList
    set(value) = recyclerListDiffer.submitList(value)

    var startedDate = "11.06.2020 00:00:00"
    var endedDate = "12.06.2020 00:00:00"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_list_recycler_row,parent,false)
        return StationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val stationName = holder.itemView.findViewById<TextView>(R.id.stationNameTextView)
        val stationAddress = holder.itemView.findViewById<TextView>(R.id.stationAdressTextView)
        val station = stations[position]

        holder.itemView.apply {
            stationName.text = station.Name
            stationAddress.text = station.Adress
            setOnClickListener {
                val action =
                    SecondStageFragmentDirections.actionSecondStageFragmentToDetailFragment(
                        stationId = station.Id,
                        stationName = station.Name,
                        startDate = startedDate,
                        endDate = endedDate
                    )
                Navigation.findNavController(it).navigate(action)
            }
        }

    }
}