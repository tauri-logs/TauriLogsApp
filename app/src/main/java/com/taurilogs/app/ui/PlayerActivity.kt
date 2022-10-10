package com.taurilogs.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taurilogs.app.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)

        binding.expandableListView.setAdapter(WeekListAdapter(this, listOf("1", "2"), hashMapOf("1" to listOf("11", "12"), "2" to listOf("21", "22"))))
        setContentView(binding.root)
    }
}
