package com.taurilogs.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taurilogs.app.LogService
import com.taurilogs.app.PlayerViewModel
import com.taurilogs.app.databinding.ActivityPlayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val owner = this
        setupObservers()
        playerViewModel.getWeekListAdapter(owner).observe(owner) { adapter ->
            binding.expandableListView.setAdapter(adapter)
            binding.expandableListView.setOnChildClickListener { _, _, _, _, id ->
                Log.d("Tauri Logs", "Clicked on log: $id")
                playerViewModel.fetchLogDetails(id)
                true
            }
        }
        setContentView(binding.root)
    }

    fun setupObservers() {
        playerViewModel.searchFinished.observe(this) {
            var visibility: Int = View.GONE
            if (it.success) {
                Intent(this, LogActivity::class.java).apply {
                    startActivity(this)
                }
            } else {
                if (it.errorMessage != null) {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                } else {
                    visibility = View.VISIBLE
                }
            }
            Log.d("Player Activity", "Search finished")
            binding.progressBarCyclic.visibility = visibility
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("PlayerActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PlayerActivity", "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d("PlayerActivity", "onStart")
    }
}
