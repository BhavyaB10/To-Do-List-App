package com.example.to_dolistapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ToDoAdapter(private var data : List<CardInfo>): RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView =itemView.findViewById(R.id.title)
        val priority:TextView=itemView.findViewById(R.id.priority)
        val layout:LinearLayout=itemView.findViewById(R.id.myLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(data[position].priority.lowercase(Locale.getDefault())){

            "high"->holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium"->holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else->holder.layout.setBackgroundColor(Color.parseColor("#00ac5f"))
        }




        holder.title.text=data[position].title
        holder.priority.text=data[position].priority
        holder.itemView.setOnClickListener {

            val intent= Intent(holder.itemView.context,UpdateData::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }


    }

}