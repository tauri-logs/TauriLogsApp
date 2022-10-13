package com.taurilogs.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.taurilogs.app.SearchViewModel
import com.taurilogs.app.databinding.ActivitySearchBinding
import com.taurilogs.app.enums.RealmEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SearchActivity", "onCreate")
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val realms = RealmEnum.values().map { it.realm }
        binding.spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, realms)
        binding.button.setOnClickListener { _ ->
            searchViewModel.fetchLogs(
                RealmEnum.values()[binding.spinner.selectedItemPosition],
                binding.username.text.toString(),
                50
            )
        }
        setupObservers()
        val view = binding.root
        setContentView(view)
    }

    private fun setupObservers() {
        searchViewModel.searchFinished.observe(this) {
            var visibility: Int = View.GONE
            if (it.success) {
                Intent(this, PlayerActivity::class.java).apply {
                    startActivity(this)
                }
            } else {
                if (it.errorMessage != null) {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                } else {
                    visibility = View.VISIBLE
                }
            }
            Log.d("Tauri Logs", "Search finished")
            binding.progressBarCyclic.visibility = visibility
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SearchActivity", "onDestroy")
    }
}
