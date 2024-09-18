package com.example.loginapp.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginapp.R
import com.example.loginapp.adapter.FarmAdapter
import com.example.loginapp.databinding.ActivityFarmListBinding
import com.example.loginapp.model.Farm
import android.content.Intent


class FarmListActivity : BaseActivity() {

    private lateinit var binding: ActivityFarmListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usamos ViewBinding para inflar el layout de la actividad
        binding = ActivityFarmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos el Drawer y el NavigationView
        setupDrawer()

        // Crear una lista de ejemplo de fincas
        val farmList = listOf(
            Farm(id = 1, name = "Finca 1", area = 100.0, unit = "m²"),
            Farm(id = 2, name = "Finca 2", area = 200.0, unit = "hectáreas")
        )

        // Configuramos el RecyclerView con un LinearLayoutManager y el Adapter
        binding.farmRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.farmRecyclerView.adapter = FarmAdapter(farmList)

        // Configuramos el botón para agregar una nueva finca
        binding.addFarmButton.setOnClickListener {
            val intent = Intent(this, FarmActivity::class.java)  // Asegúrate de que FarmActivity esté configurada
            startActivity(intent)
        }
    }
}
