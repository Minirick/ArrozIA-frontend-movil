package com.example.loginapp

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Manejar los clics en los elementos del menú
            when (menuItem.itemId) {
                R.id.nav_dashboard -> {
                    // Lógica para abrir el Dashboard
                }
                R.id.nav_login -> {
                    // Lógica para abrir la Vista Login
                }
                R.id.nav_roles -> {
                    // Lógica para abrir la Vista Roles
                }
                R.id.nav_permissions -> {
                    // Lógica para abrir la Vista Permiso
                }
                R.id.nav_user -> {
                    // Lógica para abrir la Vista de Usuario
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(findViewById(R.id.nav_view))
        return true
    }
}
