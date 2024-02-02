package com.example.testandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroid.databinding.EventItemBinding
import com.example.testandroid.domain.models.EventModel

class EventAdapter(): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    var data: List<EventModel> = listOf()

    class EventViewHolder(view: View): RecyclerView.ViewHolder(view){
        var binding : EventItemBinding? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = EventViewHolder(binding.root)
        viewHolder.binding = binding
        return viewHolder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val eventType = "$position - ${data[position].eventType}"
        holder.binding!!.tvEventType.text = eventType
        holder.binding!!.tvEventDate.text = data[position].eventDate
    }

}