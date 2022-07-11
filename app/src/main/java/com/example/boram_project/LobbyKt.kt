package com.example.boram_project

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.JavascriptInterface
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine
import kotlin.jvm.internal.Intrinsics

class LobbyKt : AppCompatActivity(){
    var stnum:String = "null"
    var stnum_int:Int = 0;
    var todaydate:Int = 20220711

    lateinit var stnum_tx:TextView
    lateinit var logout_bt:Button

    lateinit var today_sc_tx:TextView
    lateinit var today_food_tx:TextView
    lateinit var intent_sc:Intent

    lateinit var date_num_1:TextView
    lateinit var date_num_2:TextView

    var currenttime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lobby)


        logout_bt = findViewById(R.id.logout_bt)
        today_sc_tx = findViewById(R.id.today_sc_tx)
        today_food_tx = findViewById(R.id.today_food_tx)
        date_num_1 = findViewById(R.id.date_num_1)
        date_num_2 = findViewById(R.id.date_num_2)


        stnum=intent.getStringExtra("stnum").toString()
        stnum_int = stnum.toInt()
        todaydate = currenttime.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()
        date_num_1.text = todaydate.toString()
        date_num_2.text = todaydate.toString()

        logout_bt.text = stnum
        today_sc_tx.text="로딩중.."
        today_food_tx.text = "로딩중.."

        load_all()

    }

    fun logout_btclick(view:View){
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    fun load_all(){


        sc_load(3,5,todaydate,0)
        food_load(todaydate,0)
    }

    fun sc_load(search_grade:Int,search_class:Int,search_date:Int,whichupdate:Int) {
        var search_result = ""

        database = Firebase.database.reference
        val getdata = database.child(search_grade.toString()+"학년").child(search_class.toString()+"반").child("시간표").child(search_date.toString()).get()
        getdata.addOnSuccessListener {

            if(it.getValue()==null){
                search_result = "정보 없음"
            }
            else{
                for(i:Int in 1..7){
                    search_result = search_result + i.toString() + "교시 : " + it.child(i.toString()).getValue()+"\n"
                }
            }
        }.addOnFailureListener(){
            search_result = "불러오기 오류"
        }.addOnCompleteListener(){
            if(whichupdate==0){
                today_sc_tx.text = search_result
            }

        }

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
                today_food_tx.text = search_result
            }
        }

    }

    fun sc_click(view: View){
        intent_sc = Intent(this,ScKt::class.java)

        startActivity(intent_sc)
    }

    fun food_click(view: View){
        intent_sc = Intent(this,FoodKt::class.java)

        startActivity(intent_sc)
    }
}