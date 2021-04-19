package com.eseo.projet_final_android.data
import android.content.Context
import android.content.SharedPreferences


class LocalPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "MyPref",
        Context.MODE_PRIVATE
    )

    fun addToHistory(newEntry: String){ //Ajoute la localisation à l'historique puis l'enregistre dans le SharedPreferences
        val history = this.getHistory()
        history?.add(newEntry)
        clear()
        sharedPreferences.edit().putStringSet("historique", history).apply()
    }

     fun getHistory(): MutableSet<String>? { //Renvoie l'historique présent dans le SharedPreferences
        return sharedPreferences.getStringSet("historique",  HashSet<String>())
    }

    fun clear(){ //Vide l'historique du SharedPreferences
        sharedPreferences.edit().remove("historique").apply()
    }

    companion object {
        private var INSTANCE: LocalPreferences? = null

        fun getInstance(context: Context): LocalPreferences {
            return INSTANCE?.let {
                INSTANCE
            } ?: run {
                INSTANCE = LocalPreferences(context)
                return INSTANCE!!
            }
        }
    }

}
