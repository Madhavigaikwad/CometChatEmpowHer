package com.example.cometchatandroidsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.cometchatandroidsample.R

class GroupsAdapter(private val context: Context, private val groups: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return groups.size
    }

    override fun getItem(position: Int): Any {
        return groups[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            // Inflate the view for the list item
            view = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Set the group name to the TextView
        viewHolder.groupNameTextView.text = groups[position]

        return view
    }

    // ViewHolder to optimize view lookup
    private class ViewHolder(view: View) {
        val groupNameTextView: TextView = view.findViewById(R.id.groupNameTextView)
    }
}
