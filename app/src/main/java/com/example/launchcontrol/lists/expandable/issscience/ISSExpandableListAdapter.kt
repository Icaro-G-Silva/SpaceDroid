package com.example.launchcontrol.lists.expandable.issscience

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.launchcontrol.R

class ISSExpandableListAdapter(val context: Context, private val listTitle: List<String>, private val listDetails: HashMap<String, List<String>>): BaseExpandableListAdapter() {

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return listTitle.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listTitle[groupPosition]
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val title: String = getGroup(groupPosition) as String
        var actualView: View? = convertView
        if(actualView == null) {
            actualView = LayoutInflater.from(context).inflate(R.layout.expandable_list_group, parent, false)
        }
        val titleView: TextView = actualView!!.findViewById(R.id.expandableListTitle)
        titleView.typeface = Typeface.DEFAULT_BOLD
        titleView.text = title

        return actualView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listDetails[listTitle[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listDetails[listTitle[groupPosition]]!![childPosition]
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val item: String = getChild(groupPosition, childPosition) as String
        var actualView: View? = convertView
        if(actualView == null) {
            actualView = LayoutInflater.from(context).inflate(R.layout.expandable_list_item, parent, false)
        }
        val itemView: TextView = actualView!!.findViewById(R.id.expandedListItem)
        itemView.text = item

        return actualView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

}