package com.example.boram_project

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LobbyKt : AppCompatActivity(){
    var stnum:String = "null"
    lateinit var stnum_tx:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby)

        stnum=intent.getStringExtra("stnum").toString()
        stnum_tx = findViewById(R.id.showstnum_tx)
        stnum_tx.text = "학번 : " + stnum.toString()
    }
}