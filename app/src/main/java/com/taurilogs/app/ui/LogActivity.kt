package com.taurilogs.app.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.taurilogs.app.LogService
import com.taurilogs.app.R
import com.taurilogs.app.databinding.ActivityLogBinding
import org.koin.android.ext.android.inject

class LogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogBinding
    private val service: LogService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        for (member in service.raidDetail?.members!!) {
            val tbr = layoutInflater.inflate(R.layout.log_table_row, binding.tableLayout, false)
            tbr.findViewById<TextView>(R.id.name).apply {
                text = member.name
                this.setTextColor(Color.parseColor(member.playerClass.color))
            }
            tbr.findViewById<TextView>(R.id.dps).text = String.format("%,d", member.dps)
            tbr.findViewById<TextView>(R.id.hps).text = String.format("%,d", member.hps)
            binding.tableLayout.addView(tbr)
        }
        setContentView(binding.root)
    }
}
