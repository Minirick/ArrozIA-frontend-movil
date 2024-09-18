package com.example.loginapp.view

import android.content.Context
import android.content.Intent
import androidx.drawerlayout.widget.DrawerLayout
import com.example.loginapp.R
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity


class DrawerHandler(private val context: Context, private val drawerLayout: DrawerLayout, private val navigationView: NavigationView) {

    fun setupDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_dashboard -> {
                    val intent = Intent(context, DashboardActivity::class.java)
                    context.startActivity(intent)
                }
                R.id.nav_roles -> {
                    val intent = Intent(context, RolesActivity::class.java)
                    context.startActivity(intent)
                }
                R.id.nav_permissions -> {
                    val intent = Intent(context, PermissionsActivity::class.java)
                    context.startActivity(intent)
                }
                R.id.nav_user -> {
                    val intent = Intent(context, UserActivity::class.java)
                    context.startActivity(intent)
                }
                R.id.nav_farm -> {
                    val intent = Intent(context, FarmListActivity::class.java)
                    context.startActivity(intent)
                }
                R.id.nav_logout -> {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                    (context as? AppCompatActivity)?.finish()
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    fun openDrawer() {
        drawerLayout.openDrawer(navigationView)
    }
}
