package com.eseo.projet_final_android.ui.historique_recycler

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.LocalPreferences
import com.eseo.projet_final_android.data.model.HistoriqueItem
import com.eseo.projet_final_android.ui.MainActivity
import com.eseo.projet_final_android.ui.historique_recycler.adapter.HistoriqueAdapter
import com.google.gson.Gson

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
        findViewById<Button>(R.id.reset).setOnClickListener{
            LocalPreferences.getInstance(this).clear()
            finish();
            startActivity(MainActivity.getStartIntent(this));
        }
        val rv = findViewById<RecyclerView>(R.id.liste_historique)
        rv.layoutManager = LinearLayoutManager(this)
        var allhistorique = LocalPreferences.getInstance(this).getHistory()
        val arrayHistory = ArrayList<HistoriqueItem>()
        if (allhistorique != null) {
            for(element in allhistorique){
                val histoitem = Gson().fromJson(element, HistoriqueItem::class.java)
                arrayHistory.add(histoitem)
            }
        }
        rv.adapter = HistoriqueAdapter(arrayHistory)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}

