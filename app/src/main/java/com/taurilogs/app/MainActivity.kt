package com.taurilogs.app

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.taurilogs.app.databinding.ActivityMainBinding
import com.taurilogs.app.enums.RealmEnum

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val realms = RealmEnum.values().map { it.realm }
        binding.spinner.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, realms)

        val view = binding.root
        setContentView(view)
    }

}
