package com.eseo.projet_final_android.ui.parametres_recycler

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.BuildConfig
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.model.SettingsItem
import com.eseo.projet_final_android.ui.parametres_recycler.adapter.ParameterAdapter

class ParameterActivity : AppCompatActivity() {

    companion object{
        fun getStartIntent(context: Context): Intent {
            return Intent(context, ParameterActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameter)
        supportActionBar?.apply {
            setTitle(R.string.settings)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val rv = findViewById<RecyclerView>(R.id.liste_parametres)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ParameterAdapter(arrayOf(
            SettingsItem("Paramètres", R.drawable.settings) {
                startActivity( Intent( Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID) ) )
            },
            SettingsItem("Paramètres de localisation", R.drawable.map) {
                val targetIntent = Intent().apply {
                    action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                }
                startActivity(targetIntent)
            },
            SettingsItem("Carte", R.drawable.map) {
                startActivity( Intent( Intent.ACTION_VIEW, Uri.parse(getString(R.string.ESE0_localisation)) ) )
            },
            SettingsItem("Site de l'ESEO", R.drawable.logo_eseo) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://eseo.fr/")))
            },
            SettingsItem("Me contacter", R.drawable.email) {
                startActivity(Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "baptiste.guerin@reseau.eseo.fr", null)))
            }
        ))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}