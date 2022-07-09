package com.example.boram_project
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.view.View
import android.widget.Toast
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

    fun firebasetestclick(view: View){
        var toast:Toast = Toast.makeText(this,"NULL",Toast.LENGTH_SHORT)
        database = Firebase.database.reference
        database.child("test").setValue(LocalDateTime.now()).addOnSuccessListener {

            toast.cancel()
            toast = Toast.makeText(applicationContext,"성공!",Toast.LENGTH_SHORT)
            toast.show()
        }.addOnFailureListener(){
            toast.cancel()
            toast = Toast.makeText(applicationContext,"실패",Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}