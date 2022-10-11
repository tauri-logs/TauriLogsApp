package com.taurilogs.app.ui

import android.os.Bundle
import android.util.Log
//import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.taurilogs.app.TauriViewModel
import com.taurilogs.app.databinding.ActivityPlayerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private val tauriViewModel: TauriViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val owner = this
        tauriViewModel.getLogs().value?.apply {
            tauriViewModel.getWeeks(this).observe(owner) { weeks ->
                //TODO: create in coroutine
                binding.expandableListView.setAdapter(WeekListAdapter(owner, weeks))
            }
        }
        setContentView(binding.root)
    }
}
