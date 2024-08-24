package com.example.loginapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.loginapp.R
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)

        val menuButton: ImageButton = findViewById(R.id.menu_button)

        // Configurar el botón de menú para abrir el DrawerLayout
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(findViewById(R.id.nav_view))
        }

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard -> {
                    // Redirigir a DashboardActivity
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_roles -> {
                    // Redirigir a RolesActivity
                    val intent = Intent(this, RolesActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_permissions -> {
                    // Redirigir a PermissionsActivity
                    val intent = Intent(this, PermissionsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_user -> {
                    // Redirigir a UserActivity
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    // Cerrar sesión y volver a LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra el HomeActivity para que no se pueda volver al presionar atrás
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
