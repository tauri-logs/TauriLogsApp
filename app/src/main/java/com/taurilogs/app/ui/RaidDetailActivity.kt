package com.taurilogs.app.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import com.taurilogs.app.R
import com.taurilogs.app.databinding.ActivityRaidDetailBinding
import com.taurilogs.app.enums.ui.SortEnum
import com.taurilogs.app.viewmodels.RaidDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RaidDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaidDetailBinding
    private val viewModel: RaidDetailViewModel by viewModel()
    private val rows: MutableList<TableRow> = mutableListOf()
    private val headerCols: MutableList<View> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaidDetailBinding.inflate(layoutInflater)
        createHeaders()
        populateRows()
        setContentView(binding.root)
    }

    private fun populateRows() {
        for (member in viewModel.members) {
            val tbr = layoutInflater.inflate(R.layout.rd_table_row, binding.tableLayout, false) as TableRow
            tbr.findViewById<TextView>(R.id.name).apply {
                text = member.name
                this.setTextColor(Color.parseColor(member.playerClass.color))
            }
            for (header in viewModel.headers) {
                val column = layoutInflater.inflate(R.layout.rd_table_element, tbr, false)
                val fieldValue = member.javaClass.getDeclaredField(header.propertyName).apply {
                    isAccessible = true
                }.get(member)!!
                val text = if (header.isNumeric) String.format("%,d", fieldValue) else fieldValue.toString()
                column.findViewById<TextView>(R.id.log_table_element).text = text
                tbr.addView(column)
            }
            rows.add(tbr)
            binding.tableLayout.addView(tbr)
        }
    }

    private fun createHeaders() {
        for (header in viewModel.headers) {
            binding.headerRow.addView(
                layoutInflater.inflate(R.layout.rd_table_header_element, binding.headerRow, false)
                    .apply {
                        val textView = findViewById<TextView>(R.id.log_header_title)
                        textView.text = header.displayName
                        textView.setOnClickListener {
                            header.sort = header.sort.next()
                            viewModel.sortMembers(header.sort, header.propertyName)
                            (it as TextView).setCompoundDrawablesWithIntrinsicBounds(
                                getSortIcon(header.sort), 0, 0, 0
                            )
                            reRenderRows()
                        }
                    })
        }
    }

    private fun reRenderRows() {
        for (row in rows) {
            binding.tableLayout.removeView(row)
        }
        rows.clear()
        populateRows()
    }

    private fun getSortIcon(sortEnum: SortEnum): Int {
        return when (sortEnum) {
            SortEnum.NONE -> 0
            SortEnum.ASCENDING -> R.drawable.sharp_arrow_circle_up_24dp
            SortEnum.DESCENDING -> R.drawable.sharp_arrow_circle_down_24dp
        }
    }
}
