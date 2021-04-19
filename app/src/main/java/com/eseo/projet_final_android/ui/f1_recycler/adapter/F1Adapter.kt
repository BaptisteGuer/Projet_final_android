package com.eseo.projet_final_android.ui.f1_recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.model.Races


class F1Adapter(private val raceslist: List<Races>) : RecyclerView.Adapter<F1Adapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(data: Races) {
            itemView.findViewById<TextView>(R.id.race_name).text = data.raceName
            itemView.findViewById<TextView>(R.id.race_date).text = data.date + " " + data.time
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.races_list, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.showItem(raceslist[position])
        }

    override fun getItemCount(): Int {
        return raceslist.size
    }
}