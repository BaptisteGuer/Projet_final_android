package com.eseo.projet_final_android.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.LocalPreferences
import com.eseo.projet_final_android.ui.historique_recycler.HistoriqueActivity
import com.eseo.projet_final_android.ui.parametres_recycler.ParameterActivity

class MainActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.param).setOnClickListener {
            startActivity(ParameterActivity.getStartIntent(this))
        }
        findViewById<Button>(R.id.go_to_locate).setOnClickListener {
            startActivity(MapActivity.getStartIntent(this))
        }

        findViewById<Button>(R.id.histo).setOnClickListener {
            if (LocalPreferences.getInstance(this).getHistory().isNullOrEmpty()) {
                Toast.makeText(this, getString(R.string.historique_vide), Toast.LENGTH_SHORT).show()
            } else {
                startActivity(HistoriqueActivity.getStartIntent(this))
            }
        }
    }
}