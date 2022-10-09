package com.taurilogs.app

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.databinding.ActivityMainBinding
import com.taurilogs.app.enums.RealmEnum
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tauriViewModel: TauriViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val realms = RealmEnum.values().map { it.realm }
        binding.spinner.adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, realms)
        binding.button.setOnClickListener { _ ->
            tauriViewModel.getLogs(RealmEnum.EVERMOON, "Stepan", 10).observe(this) { logs ->
                Log.d("MainActivity", logs.toString())
            }
        }
        val view = binding.root
        setContentView(view)
    }

}
