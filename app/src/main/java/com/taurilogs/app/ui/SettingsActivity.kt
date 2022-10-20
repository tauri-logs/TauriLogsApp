package com.taurilogs.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import com.taurilogs.app.R
import com.taurilogs.app.services.SettingsService
import com.taurilogs.app.databinding.ActivitySettingsBinding
import org.koin.android.ext.android.inject

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val settingsService: SettingsService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val prefs = getSharedPreferences(getString(R.string.preference_file_settings), MODE_PRIVATE)
        settingsService.loadHeadersFromPrefs(prefs)
        settingsService.rdHeaders.forEach {
            binding.settingsTable.addView(
                layoutInflater.inflate(R.layout.settings_rd_display_row, binding.settingsTable, false).apply {
                    findViewById<TextView>(R.id.settings_rd_display_row_title).text = it.displayName
                    findViewById<CheckBox>(R.id.settings_rd_display_row_checkbox).apply {
                        isChecked = it.display
                        setOnCheckedChangeListener { _, isChecked ->
                            it.display = isChecked
                            prefs.edit().apply {
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
