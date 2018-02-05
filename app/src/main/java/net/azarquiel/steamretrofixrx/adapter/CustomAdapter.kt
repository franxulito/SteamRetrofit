package net.azarquiel.steamretrofixrx.adapter

/**
 * Created by Alvaro on 05/02/2018.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_gamedrive.view.*
import net.azarquiel.steamretrofixrx.model.GameDrive


/**
 * Created by pacopulido on 17/9/17.
 */
class CustomAdapter(val context: Context,
                    val layout: Int,
                    var dataList: ArrayList<GameDrive>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {





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



    inner class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {


        fun bind(dataItem: GameDrive){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem

            itemView.tvNombre.text = dataItem.name


           /* itemView.setOnClickListener(View.OnClickListener{
                onItemClick(dataItem)
            })*/
        }



        /*
        private fun onItemClick(dataItem: GameDrive) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("tipo", dataItem.tipo)
            context.startActivity(intent)
        }
*/





    }
}