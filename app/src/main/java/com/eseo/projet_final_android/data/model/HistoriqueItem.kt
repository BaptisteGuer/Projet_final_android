package com.eseo.projet_final_android.data.model


// Définition de la Class qui sera dans notre RecyclerView
data class HistoriqueItem(val latitude: Double,val longitude: Double, val adresse: String, val distance: Double, var onClick: (() -> Unit)? = null)



