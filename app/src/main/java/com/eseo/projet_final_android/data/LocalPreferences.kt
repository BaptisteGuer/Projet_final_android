package com.eseo.projet_final_android.data
import android.content.Context
import android.content.SharedPreferences

class LocalPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    fun saveStringValue(yourValue: String?) {
        sharedPreferences.edit().putString("saveStringValue", yourValue).apply()
    }

    fun getSaveStringValue(): String? {
        return sharedPreferences.getString("saveStringValue", null)
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
