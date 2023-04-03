package com.example.thigk2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db : DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* CON GA CON */

        btnreadcourse.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        db = DBHelper(this)

        btncourse.setOnClickListener {

            val name = edtname.text.toString()
            val phone =edtphone.text.toString()
            val email = edtemail.text.toString()
            val savedatas = db.savedata(name, phone, email)
            if (TextUtils.isEmpty(name)){
                Toast.makeText(this, "ADD NAME - ADD TRACK - ADD DURATION", Toast.LENGTH_SHORT).show()
            }else {
                if (savedatas == true) {
                    Toast.makeText(this, "save contact", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "exists contact", Toast.LENGTH_SHORT).show()
                }
            }
            //(name)||(TextUtils.isEmpty(Description))
        }

    }


}