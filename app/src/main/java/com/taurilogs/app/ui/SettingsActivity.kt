package com.taurilogs.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.taurilogs.app.R
import com.taurilogs.app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        getPreferences(MODE_PRIVATE)
        for (i in 1..10) {
            Log.d("SettingsActivity", "onCreate: $i")
            binding.settingsTable.addView(
                layoutInflater.inflate(R.layout.settings_rd_display_row, binding.settingsTable, false).apply {
                    findViewById<TextView>(R.id.settings_rd_display_row_title).text = "Row $i"
                }
            )
        }
        setContentView(binding.root)
    }
}
