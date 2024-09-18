package com.example.loginapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.ItemFarmBinding
import com.example.loginapp.model.Farm

class FarmAdapter(private val farmList: List<Farm>) : RecyclerView.Adapter<FarmAdapter.FarmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        // Usar ViewBinding para inflar la vista
        val binding = ItemFarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FarmViewHolder, position: Int) {
        val farm = farmList[position]
        holder.bind(farm)
    }

    override fun getItemCount(): Int {
        return farmList.size
    }

    // ViewHolder para mantener las referencias a las vistas usando ViewBinding
    class FarmViewHolder(private val binding: ItemFarmBinding) : RecyclerView.ViewHolder(binding.root) {

        // Función para enlazar los datos con las vistas
        fun bind(farm: Farm) {
            binding.farmName.text = farm.name
            binding.farmArea.text = "${farm.area} ${farm.unit}"
        }
    }
}
