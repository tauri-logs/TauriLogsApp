package com.taurilogs.app.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import com.taurilogs.app.R
import com.taurilogs.app.databinding.ActivityRaidDetailBinding
import com.taurilogs.app.enums.SpecEnum
import com.taurilogs.app.enums.ui.SortEnum
import com.taurilogs.app.models.ui.RaidDetailColumn
import com.taurilogs.app.viewmodels.RaidDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RaidDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaidDetailBinding
    private val viewModel: RaidDetailViewModel by viewModel()
    private val rows: MutableList<TableRow> = mutableListOf()
    private val headerCols: MutableList<TextView> = mutableListOf()

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
                setTextColor(Color.parseColor(member.playerClass.color))
                addSpecIconToName(member.spec, this)
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

    private fun addSpecIconToName(spec: SpecEnum, view: TextView) {
        val draw = resources.getDrawable(spec.resourceId, null)
        var dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20f, resources.displayMetrics).toInt()
        dimension += dimension / 10
        draw.setBounds(0, 0, dimension, dimension)
        view.setCompoundDrawables(draw, null, null, null)
        view.compoundDrawablePadding = 10
    }

    private fun createHeaders() {
        for (header in viewModel.headers) {
            binding.headerRow.addView(
                layoutInflater.inflate(R.layout.rd_table_header_element, binding.headerRow, false)
                .apply {
                    val textView = findViewById<TextView>(R.id.log_header_title)
                    textView.text = header.displayName
                    setSortIcon(textView, header.sort)
                    textView.setOnClickListener { sortByHeader(header, it as TextView) }
                    headerCols.add(textView)
                }
            )
        }
    }

    private fun sortByHeader(header: RaidDetailColumn, view: TextView) {
        header.sort = header.sort.next()
        viewModel.headers.forEachIndexed { index, raidDetailColumn ->
            if (raidDetailColumn.display && raidDetailColumn.sort != SortEnum.NONE && raidDetailColumn != header) {
                raidDetailColumn.sort = SortEnum.NONE
                setSortIcon(headerCols[index], raidDetailColumn.sort)
            }
        }
        viewModel.sortMembers(header.sort, header.propertyName)
        setSortIcon(view, header.sort)
        reRenderRows()
    }

    private fun setSortIcon(textView: TextView, sort: SortEnum) {
        textView.setCompoundDrawablesWithIntrinsicBounds(sort.sortIcon, 0, 0, 0)
    }

    private fun reRenderRows() {
        for (row in rows) {
            binding.tableLayout.removeView(row)
        }
        rows.clear()
        populateRows()
    }
}
