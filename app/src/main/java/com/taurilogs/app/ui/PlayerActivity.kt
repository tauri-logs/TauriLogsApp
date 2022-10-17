package com.taurilogs.app.ui

import android.os.Bundle
import android.util.Log
import com.taurilogs.app.viewmodels.PlayerViewModel
import com.taurilogs.app.databinding.ActivityPlayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : CustomActivity() {

    private lateinit var binding: ActivityPlayerBinding
    override val viewModel: PlayerViewModel by viewModel()
    override val nextDestination: Class<*> = RaidDetailActivity::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val owner = this
        viewModel.getWeekListAdapter(owner).observe(owner) { adapter ->
            binding.expandableListView.setAdapter(adapter)
            binding.expandableListView.setOnChildClickListener { _, _, _, _, id ->
                Log.d("Tauri Logs", "Clicked on log: $id")
                viewModel.fetchLogDetails(id)
                true
            }
        }
        setContentView(binding.root)
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
