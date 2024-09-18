package com.example.loginapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.loginapp.R
import com.google.android.material.navigation.NavigationView

open class BaseActivity : AppCompatActivity() {

    protected var drawerLayout: DrawerLayout? = null
    protected var navigationView: NavigationView? = null

    // Llamar después de configurar el layout en la actividad hija
    protected fun setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        if (drawerLayout != null && navigationView != null) {
            setupDrawerMenu()
        }
    }

    protected fun setupDrawerMenu() {
        val menuButton: ImageButton? = findViewById(R.id.menu_button)

        // Configurar el botón de menú si existe
        menuButton?.setOnClickListener {
            drawerLayout?.openDrawer(navigationView!!)
        }

        navigationView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard -> startActivity(Intent(this, DashboardActivity::class.java))
                R.id.nav_roles -> startActivity(Intent(this, RolesActivity::class.java))
                R.id.nav_permissions -> startActivity(Intent(this, PermissionsActivity::class.java))
                R.id.nav_user -> startActivity(Intent(this, UserActivity::class.java))
                R.id.nav_farm -> startActivity(Intent(this, FarmListActivity::class.java))
                R.id.nav_logout -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout?.closeDrawers()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout?.openDrawer(navigationView!!)
        return true
    }
}
