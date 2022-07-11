package com.example.boram_project

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class FoodKt:AppCompatActivity() {
    var currenttime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
    var now_date = 0

    private lateinit var database: DatabaseReference
    lateinit var sc_date_tx: TextView
    lateinit var sc_text_tx1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_time)

        sc_date_tx = findViewById(R.id.sc_date_tx)
        sc_text_tx1 = findViewById(R.id.sc_text_tx1)

        sc_go()
    }
    fun sc_left(view: View){
        currenttime=currenttime.minusDays(1)
        sc_go()
    }
    fun sc_right(view: View){
        currenttime=currenttime.plusDays(1)
        sc_go()
    }

    fun sc_go(){
        now_date = currenttime.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()
        sc_date_tx.text = now_date.toString()
        sc_text_tx1.text="로딩중.."
        food_load(now_date,0)
    }

    fun food_load(search_date:Int,whichupdate:Int) {
        var search_result = ""

        database = Firebase.database.reference
        val getdata = database.child("급식표").child(search_date.toString()).get()
        getdata.addOnSuccessListener {

            if(it.getValue()==null){
                search_result = "정보 없음"
            }
            else{
                for(i:Int in 1..10){
                    if(it.child(i.toString()).getValue()==null) break
                    search_result = search_result + it.child(i.toString()).getValue()+"\n"
                }
            }
        }.addOnFailureListener(){
            search_result = "불러오기 오류"
        }.addOnCompleteListener(){
            if(whichupdate==0){
                sc_text_tx1.text = search_result
            }
        }

    }

}