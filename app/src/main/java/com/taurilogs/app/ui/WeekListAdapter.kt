package com.taurilogs.app.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.taurilogs.app.R

class WeekListAdapter(
    private val context: Context,
    private val weekTitle: List<String>,
    private val weekDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
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
        return childPosition.toLong()
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
        val expandedTextView = view.findViewById<TextView>(R.id.weekListItem)
        expandedTextView.text = getChild(groupPosition, childPosition) as String
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
