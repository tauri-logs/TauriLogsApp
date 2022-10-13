package com.taurilogs.app.ui

import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
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

        playerViewModel.getWeekListAdapter(owner).observe(owner) { adapter ->
            binding.expandableListView.setAdapter(adapter)
            binding.expandableListView.setOnChildClickListener(ExpandableListView.OnChildClickListener() {
                _, _, _, _, id ->
                Log.d("Tauri Logs", "Clicked on log: $id")
                true
            })
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
