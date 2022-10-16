package com.taurilogs.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.taurilogs.app.viewmodels.CustomViewModel
import com.taurilogs.app.databinding.FragmentLoadingBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoadingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoadingFragment : Fragment() {
    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        Log.d("Tauri Logs", "onCreateView loadingFragment")
        val parentActivity = activity
        if (parentActivity is CustomActivity) {
            subscribeToSearchFinished(parentActivity.viewModel, parentActivity.nextDestination)
        }
        return binding.root
    }

    private fun subscribeToSearchFinished(viewModel: CustomViewModel, nextDestination: Class<*>) {
        viewModel.searchFinished.observe(this) {
            var visibility: Int = View.GONE
            if (it.success) {
                Intent(activity, nextDestination).apply {
                    startActivity(this)
                }
            } else {
                if (it.errorMessage != null) {
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_LONG).show()
                } else {
                    visibility = View.VISIBLE
                }
            }
            Log.d("Search Activity", "Search finished")
            binding.loadingWheel.visibility = visibility
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment LoadingFragment.
         */
        @JvmStatic
        fun newInstance() = LoadingFragment()
    }
}
