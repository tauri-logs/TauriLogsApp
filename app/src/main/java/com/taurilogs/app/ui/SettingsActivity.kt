package com.taurilogs.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import com.taurilogs.app.R
import com.taurilogs.app.SettingsService
import com.taurilogs.app.databinding.ActivitySettingsBinding
import org.koin.android.ext.android.inject

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val settingsService: SettingsService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        settingsService.loadHeadersFromPrefs(getPreferences(MODE_PRIVATE))
        settingsService.rdHeaders.forEach {
            binding.settingsTable.addView(
                layoutInflater.inflate(R.layout.settings_rd_display_row, binding.settingsTable, false).apply {
                    findViewById<TextView>(R.id.settings_rd_display_row_title).text = it.displayName
                    findViewById<CheckBox>(R.id.settings_rd_display_row_checkbox).apply {
                        isChecked = it.display
                        setOnCheckedChangeListener { _, isChecked ->
                            it.display = isChecked
                            getPreferences(MODE_PRIVATE).edit().apply {
                                putBoolean(it.propertyName, it.display)
                                apply()
                            }
                        }
                    }
                }
            )
        }
        setContentView(binding.root)
    }
}
