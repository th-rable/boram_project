package com.example.boram_project
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
    fun firstlogin(view: View){
        var loginbt:Button = findViewById<Button>(R.id.firstlogin_bt)

        loginbt.isEnabled = false

        var stnum_inp=findViewById<TextInputEditText>(R.id.stnum_inp).text
        var stnum_inp_lay=findViewById<TextInputLayout>(R.id.stnum_inp_lay)

        if(stnum_inp==null || stnum_inp.length!=5){
            stnum_inp_lay.error = "학번은 5자입니다"
            loginbt.isEnabled = true
        }
        else{
            stnum_inp_lay.error = ""
            var intent = Intent(this,LobbyKt::class.java)
            intent.putExtra("stnum",stnum_inp.toString())
            startActivity(intent)

            finish()
        }
    }
    fun firebasetestclick(view: View){
        var toast:Toast = Toast.makeText(this,"NULL",Toast.LENGTH_SHORT)
        database = Firebase.database.reference
        database.child("test").setValue(LocalDateTime.now()).addOnSuccessListener {

            toast.cancel()
            toast = Toast.makeText(this,"성공!",Toast.LENGTH_SHORT)
            toast.show()
        }.addOnFailureListener(){
            toast.cancel()
            toast = Toast.makeText(this,"실패",Toast.LENGTH_SHORT)
            toast.show()
        }


    }
}