package com.eseo.projet_final_android.data.model

// Définition de la Class qui sera dans notre RecyclerView
data class SettingsItem(val name: String, val icon: Int, val onClick: (() -> Unit)? = null)


