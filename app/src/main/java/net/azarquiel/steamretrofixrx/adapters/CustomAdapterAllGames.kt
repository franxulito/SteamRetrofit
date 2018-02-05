package net.azarquiel.steamretrofixrx.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.azarquiel.steamretrofixrx.model.Games

/**
 * Created by agust on 05/02/2018.
 */
class CustomAdapterAllGames (val context: Context,
                             val layout: Int,
                             val dataList: List<Games>) : RecyclerView.Adapter<CustomAdapterAllGames.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Games) {
            // itemview es el item de diseÃ±o
            // al que hay que poner los datos del
            // objeto dataItem
            /*
            Log.d("****Agustin****",dataItem.c[5].v)

            itemView.tvTramaRow.text = dataItem.c[5].v
            itemView.tvAnnioRow.text = dataItem.c[3].v
            itemView.tvDirectorRow.text =dataItem.c[4].v
            itemView.tvTituloRow.text = dataItem.c[2].v
            Picasso.with(context).load(dataItem.c[6].v).into(itemView.ivPosterRow)

            */
            Log.d("****Agustin****", "kk")

            /*
            itemView.setOnClickListener(View.OnClickListener{
                onItemClick(dataItem)
            })
            */
        }
        /*
        private fun onItemClick(dataItem: Games) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("pulsedFilm", dataItem.toString())
            context.startActivity(intent)
        }
        */
    }

}