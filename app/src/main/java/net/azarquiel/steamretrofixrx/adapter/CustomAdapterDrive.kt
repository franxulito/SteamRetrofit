package net.azarquiel.steamretrofixrx.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.drive_row.view.*
import net.azarquiel.steamretrofixrx.model.GameDrive


class CustomAdapterDrive(val context: Context,
                         val layout: Int,
                         val dataList: ArrayList<GameDrive>): RecyclerView.Adapter<CustomAdapterDrive.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {

        fun bind(dataItem: GameDrive, position: Int){




        }

    }

}