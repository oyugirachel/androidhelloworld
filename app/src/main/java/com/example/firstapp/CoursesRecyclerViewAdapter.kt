package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_course_item.view.*


class CoursesRecyclerViewAdapter(val courseList: List<Course>
) :
    RecyclerView.Adapter<CoursesRecyclerViewAdapter.NamesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_course_item, parent, false)
        return NamesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }


    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.rowView.tvCourseId.text = courseList[position].courseId
        holder.rowView.tvCourseCode.text = courseList[position].courseCode
        holder.rowView.tvCourseName.text = courseList[position].courseName
        holder.rowView.tvDescription.text = courseList[position].description
        holder.rowView.tvInstructor.text = courseList[position].instructor

    }

    class NamesViewHolder(val rowView: View) : RecyclerView.ViewHolder(rowView)
}