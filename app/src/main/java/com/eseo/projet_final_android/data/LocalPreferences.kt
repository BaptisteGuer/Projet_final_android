package com.eseo.projet_final_android.data
import android.content.Context
import android.content.SharedPreferences


class LocalPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "MyPref",
        Context.MODE_PRIVATE
    )

    fun addToHistory(newEntry: String){
        val history = this.getHistory()
        history?.add(newEntry);
        sharedPreferences.edit().putStringSet("histories", history).apply()
    }

     fun getHistory(): MutableSet<String>? {
        return sharedPreferences.getStringSet("histories",  HashSet<String>())
    }

    fun clear(){
        sharedPreferences.edit().remove("histories").apply()
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
