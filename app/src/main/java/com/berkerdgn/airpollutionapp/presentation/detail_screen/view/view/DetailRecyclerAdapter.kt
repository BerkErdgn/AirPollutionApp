package com.berkerdgn.airpollutionapp.presentation.detail_screen.view.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.airpollutionapp.R
import com.berkerdgn.airpollutionapp.data.remote.dto.StationDetailModelItem

class DetailRecyclerAdapter() : RecyclerView.Adapter<DetailRecyclerAdapter.DetailAdapterViewHolder>() {

    class DetailAdapterViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<StationDetailModelItem>(){
        override fun areItemsTheSame(
            oldItem: StationDetailModelItem,
            newItem: StationDetailModelItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: StationDetailModelItem,
            newItem: StationDetailModelItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var details: List<StationDetailModelItem>
        get() = recyclerListDiffer.currentList
    set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_detail_recycler_row, parent,false)
        return DetailAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return details.size
    }

    override fun onBindViewHolder(holder: DetailAdapterViewHolder, position: Int) {
        val readingTime = holder.itemView.findViewById<TextView>(R.id.readingTimeTextView3)
        val stimColor = holder.itemView.findViewById<Button>(R.id.button2)
        val pm10Concentration = holder.itemView.findViewById<TextView>(R.id.pm10ConcentrationTextView)
        val so2Concentration = holder.itemView.findViewById<TextView>(R.id.so2ConcentrationTextView)
        val o3Concentration = holder.itemView.findViewById<TextView>(R.id.o3ConcentrationTextView7)
        val no3Concentration = holder.itemView.findViewById<TextView>(R.id.no3ConcentrationTextView11)
        val coConcentration = holder.itemView.findViewById<TextView>(R.id.coConcentrationTextView13)

        val pm10Aqi = holder.itemView.findViewById<TextView>(R.id.pm10AqiTextView)
        val so2Aqi = holder.itemView.findViewById<TextView>(R.id.so2AqiTextView)
        val o3Aqi = holder.itemView.findViewById<TextView>(R.id.o3AqiTextView7)
        val no3Aqi = holder.itemView.findViewById<TextView>(R.id.no3AqiTextView11)
        val coAqi = holder.itemView.findViewById<TextView>(R.id.coAqiTextView13)

        val aQIIndex = holder.itemView.findViewById<TextView>(R.id.aQIIndexTextView)
        val contaminantParameter = holder.itemView.findViewById<TextView>(R.id.contaminantParameterTextView)
        val state = holder.itemView.findViewById<TextView>(R.id.stateTextView13)

        val detail = details[position]

        holder.itemView.apply {
            try {
                readingTime.text = detail.ReadTime
                stimColor.setBackgroundColor(Color.parseColor(detail.AQI.Color))
                pm10Concentration.text = detail.Concentration.PM10.toString()
                so2Concentration.text = detail.Concentration.SO2.toString()
                o3Concentration.text = detail.Concentration.O3.toString()
                no3Concentration.text = detail.Concentration.NO2.toString()
                coConcentration.text = detail.Concentration.CO.toString()

                pm10Aqi.text = detail.AQI.PM10.toString()
                so2Aqi.text = detail.AQI.SO2.toString()
                o3Aqi.text  = detail.AQI.O3.toString()
                no3Aqi.text = detail.AQI.NO2.toString()
                coAqi.text = detail.AQI.SO2.toString()

                aQIIndex.text = detail.AQI.AQIIndex.toString()
                contaminantParameter.text = detail.AQI.ContaminantParameter.toString()
                state.text = detail.AQI.State
            }catch (e:Exception){
                println(e.localizedMessage)
            }

        }

    }
}