package com.example.loginapp.view

import android.os.Bundle
import com.example.loginapp.R

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Configura el Drawer
        setupDrawer()
    }
}
