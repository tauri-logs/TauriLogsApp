package com.taurilogs.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.taurilogs.app.TauriViewModel
import com.taurilogs.app.databinding.ActivitySearchBinding
import com.taurilogs.app.enums.RealmEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val tauriViewModel: TauriViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val realms = RealmEnum.values().map { it.realm }
        binding.spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, realms)
        binding.button.setOnClickListener { _ ->
            tauriViewModel.getLogs(RealmEnum.EVERMOON, "Stepan", 10).observe(this) { logs ->
                Log.d("MainActivity", logs.toString())
                Intent(this, PlayerActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        val view = binding.root
        setContentView(view)
    }

}
