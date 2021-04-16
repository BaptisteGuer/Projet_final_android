package com.eseo.projet_final_android.ui.parametres_recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.data.model.SettingsItem
import com.eseo.projet_final_android.R


class ParameterAdapter(private val settingslist: Array<SettingsItem>) : RecyclerView.Adapter<ParameterAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(setting: SettingsItem) {
            itemView.findViewById<TextView>(R.id.texte).text = setting.name
            itemView.findViewById<ImageView>(R.id.logo_image).setImageResource(setting.icon)
            if(setting.onClick != null) {
                itemView.setOnClickListener {
                    setting.onClick.let { it() }
                }
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parameter_list, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.showItem(settingslist[position])
        }

    override fun getItemCount(): Int {
        return settingslist.size
    }
}