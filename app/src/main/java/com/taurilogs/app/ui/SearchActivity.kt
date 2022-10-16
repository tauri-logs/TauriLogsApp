package com.taurilogs.app.ui

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.taurilogs.app.R
import com.taurilogs.app.viewmodels.SearchViewModel
import com.taurilogs.app.databinding.ActivitySearchBinding
import com.taurilogs.app.enums.RealmEnum
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : CustomActivity() {

    private lateinit var binding: ActivitySearchBinding
    override val viewModel: SearchViewModel by viewModel()
    override val nextDestination: Class<*> = PlayerActivity::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SearchActivity", "onCreate")
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val realms = RealmEnum.values().map { it.realm }
        binding.spinner.adapter = ArrayAdapter(this, R.layout.spinner_item, realms)
        binding.button.setOnClickListener { _ ->
            viewModel.fetchLogs(
                RealmEnum.values()[binding.spinner.selectedItemPosition],
                binding.username.text.toString(),
                50
            )
        }
        val view = binding.root
        setContentView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SearchActivity", "onDestroy")
    }
}
