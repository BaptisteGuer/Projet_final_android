package com.eseo.projet_final_android.ui.f1_recycler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eseo.projet_final_android.R
import com.eseo.projet_final_android.data.model.Races
import com.eseo.projet_final_android.remote.ApiService
import com.eseo.projet_final_android.ui.f1_recycler.adapter.F1Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class F1Activity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, F1Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_races)
        supportActionBar?.apply {
            setTitle(R.string.races_planning)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        var dataItemSource: List<Races>
        val rv = findViewById<RecyclerView>(R.id.liste_races)
        rv.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.get).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    val json = ApiService.instance.readStatus()
                    runOnUiThread {
                        dataItemSource = json.MRData.raceTable.races
                        rv.adapter?.notifyDataSetChanged()
                        rv.adapter = F1Adapter(dataItemSource)
                        Toast.makeText(
                            this@F1Activity,
                            "Résultat de l'appel réseau vers " + json.MRData.url,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}