
package com.taurilogs.app.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taurilogs.app.R
import com.taurilogs.app.databinding.FragmentToolbarBinding

private const val TITLE = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ToolbarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToolbarFragment : Fragment() {
    private var title: String? = null

    private var _binding: FragmentToolbarBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentToolbarBinding.inflate(inflater, container, false)
        val toolbar = binding.toolbar
        toolbar.title = resources.getString(R.string.toolbar_title)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_settings -> {
                    Log.d("Tauri Logs", "Settings")
                    moveToDestination(SettingsActivity::class.java)
                    true
                }
                R.id.menu_search -> {
                    Log.d("Tauri Logs", "Search ${activity?.componentName?.className}")
                    moveToDestination(SearchActivity::class.java)
                    true
                }
                else -> false
            }
        }
        return binding.root
    }

    private fun moveToDestination(destinationClass: Class<*>) {
        if (activity?.componentName?.className != destinationClass.name) {
            Intent(activity, destinationClass).apply {
                startActivity(this)
            }
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
         * @param title Page title.
         * @return A new instance of fragment ToolbarFragment.
         */
        @JvmStatic
        fun newInstance(title: String) =
            ToolbarFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
    }
}
