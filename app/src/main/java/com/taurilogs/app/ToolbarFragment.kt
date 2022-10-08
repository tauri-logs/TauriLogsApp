
package com.taurilogs.app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taurilogs.app.databinding.FragmentToolbarBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
        toolbar.title = "Tauri Logs"
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_settings -> {
                    Log.d("Tauri Logs", "Settings")
                    true
                }
                R.id.menu_search -> {
                    Log.d("Tauri Logs", "Search")
                    true
                }
                else -> false
            }
        }
        return binding.root
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
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
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
