package com.example.contactapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView


class UserActivity : AppCompatActivity() {
    lateinit var avtImage :ImageView
    lateinit var nameTv : TextView
    lateinit var idTv: TextView
    lateinit var phoneTv :TextView
    lateinit var emailTv: TextView


    lateinit var callButton: ImageButton
    lateinit var messButton: ImageButton
    lateinit var emailButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        avtImage = findViewById(R.id.imageView)
        nameTv = findViewById(R.id.name_tv)
        idTv = findViewById(R.id.id_info)
        phoneTv = findViewById(R.id.phone_info)
        emailTv = findViewById(R.id.email_info)


        callButton = findViewById(R.id.call_btn)
        messButton = findViewById(R.id.mess_btn)
        emailButton = findViewById(R.id.email_btn)


        //
        val avt =intent.getIntExtra("avt",-1)
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")


        // get Data
        avtImage.setImageResource(avt)
        nameTv.text= name
        idTv.text= id
        phoneTv.text= phone
        emailTv.text= email

        // set on Click Listener

        callButton.setOnClickListener {
            val callIntent: Intent = Uri.parse("tel:${phone}").let { number ->
                Intent(Intent.ACTION_DIAL, number)
            }
            startActivity(callIntent)
            Log.v("TAG","Call action done! ")
        }

        messButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${phone}"))
                intent.putExtra("sms_body", "Bé Dương, I love you chùn chụt")
                startActivity(intent)
            } catch (e: Exception) {
                Log.v("TAG","SMS action failed")
            }

            Log.v("TAG","SMS action done! ")

        }

        emailButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {

                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
                Log.v("TAG", "Send Email action done!")
            } else {
                Log.v("TAG", "No email app found!")
            }

        }

    }


    // Main menu 2

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu_2, menu)
        return true
    }
}