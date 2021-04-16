package com.eseo.projet_final_android.ui.historique_recycler.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.model.HistoriqueItem


class HistoriqueAdapter(private val historiquelist: ArrayList<HistoriqueItem>) : RecyclerView.Adapter<HistoriqueAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(historique: HistoriqueItem) {
            itemView.findViewById<TextView>(R.id.texte).text = historique.adresse
            itemView.findViewById<TextView>(R.id.distance).text = historique.distance.toString() + " km de l'ESEO."
            if(historique.onClick != null) {
                itemView.findViewById<ImageView>(R.id.logo_image).setOnClickListener {
                    historique.onClick?.let { it() }
                }
            }
        }
        }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historique_list, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
        holder.showItem(historiquelist[position])
    }

    override fun getItemCount(): Int {
        return historiquelist.size
    }
}