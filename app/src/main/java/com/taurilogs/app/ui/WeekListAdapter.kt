package com.taurilogs.app.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.taurilogs.app.R
import com.taurilogs.app.models.Week
import java.time.format.DateTimeFormatter

class WeekListAdapter(
    private val context: Context,
    weeks: List<Week>
) : BaseExpandableListAdapter() {

    private val weekTitle: List<String> = weeks.map { getGroupName(it) }
    private val weekDetail: HashMap<String, List<List<String>>> = hashMapOf()

    init {
        weeks.forEach { week ->
            weekDetail[getGroupName(week)] =
                week.logs.map { listOf(it.difficultyName, "${it.killDate.dayOfMonth}.", it.log_id.toString()) }
        }
    }

    private fun getGroupName(week: Week): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.")
        return "${week.startDate.format(formatter)} - ${week.endDate.format(formatter)}"
    }

    override fun getGroupCount(): Int {
        return weekTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return weekDetail[weekTitle[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return weekTitle[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return weekDetail[weekTitle[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return weekDetail[weekTitle[groupPosition]]!![childPosition][2].toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val listTitle = getGroup(groupPosition) as String
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.week_list_group, parent, false)
        val textView = view.findViewById<TextView>(R.id.weekListGroup)
        textView.setTypeface(null, android.graphics.Typeface.BOLD)
        textView.text = listTitle
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.week_list_item, parent, false)
        val expandedTextViewLeft = view.findViewById<TextView>(R.id.weekListItemTextLeft)
        val expandedTextViewRight = view.findViewById<TextView>(R.id.weekListItemTextRight)
        val (left, right) = getChild(groupPosition, childPosition) as List<*>
        expandedTextViewLeft.text = left as String
        expandedTextViewRight.text = right as String
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
