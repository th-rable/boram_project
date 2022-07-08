package com.example.boram_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
// ...


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun firebasetestclick(){
        database = Firebase.database.reference


        database.child("test").setValue(LocalDateTime.now());
    }
}