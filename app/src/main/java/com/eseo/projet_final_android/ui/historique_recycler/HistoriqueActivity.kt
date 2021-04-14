package com.eseo.projet_final_android.ui.historique_recycler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.model.HistoriqueItem
import com.eseo.projet_final_android.ui.historique_recycler.adapter.HistoriqueAdapter

class HistoriqueActivity : AppCompatActivity() {

    companion object{
        fun getStartIntent(context: Context): Intent {
            return Intent(context, HistoriqueActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historique)
        supportActionBar?.apply {
            setTitle(R.string.historique)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val rv = findViewById<RecyclerView>(R.id.liste_historique)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HistoriqueAdapter(arrayOf(
            HistoriqueItem(1, "Le Mans",125.900), HistoriqueItem(2, "Angers",2.100),
        ))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}