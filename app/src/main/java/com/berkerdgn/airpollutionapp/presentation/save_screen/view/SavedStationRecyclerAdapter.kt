package com.berkerdgn.airpollutionapp.presentation.save_screen.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.data.remote.dto.saved_dto.SavedStationModel
import com.berkerdgn.airpollutionapp.presentation.second_stage_screen.view.SecondStageFragmentDirections

class SavedStationRecyclerAdapter (): RecyclerView.Adapter<SavedStationRecyclerAdapter.SavedStationViewHolder> (){

    class SavedStationViewHolder (itemView:View): RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<SavedStationModel>(){
        override fun areItemsTheSame(
            oldItem: SavedStationModel,
            newItem: SavedStationModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SavedStationModel,
            newItem: SavedStationModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var savedStations : List<SavedStationModel>
        get() = recyclerListDiffer.currentList
    set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedStationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_station_recycler_row,parent,false)
        return  SavedStationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return savedStations.size
    }

    override fun onBindViewHolder(holder: SavedStationViewHolder, position: Int) {
        val stationName = holder.itemView.findViewById<TextView>(R.id.stationNameTextView)
        val startDate = holder.itemView.findViewById<TextView>(R.id.startDateTextView)
        val endDate = holder.itemView.findViewById<TextView>(R.id.endDateTextView)
        val savedStation = savedStations[position]

        holder.itemView.apply {
            stationName.text = savedStation.stationName
            startDate.text = savedStation.startDate
            endDate.text = savedStation.endDate
            setOnClickListener {
                val action = SecondStageFragmentDirections.actionSecondStageFragmentToDetailFragment(
                    stationName = savedStation.stationName,
                    stationId = savedStation.stationID,
                    startDate = savedStation.startDate,
                    endDate = savedStation.endDate
                )
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

}