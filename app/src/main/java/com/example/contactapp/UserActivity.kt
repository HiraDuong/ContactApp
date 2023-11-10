package com.example.contactapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import listview.UserItem

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
            val phoneNumber = "0123456789" // Số điện thoại đích
            val message = "Hello, this is a message!" // Nội dung tin nhắn

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:$phoneNumber") // Gửi đến số điện thoại cụ thể


            intent.putExtra("sms_body", message)


            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Xử lý khi không có ứng dụng nào có thể xử lý Intent
                Toast.makeText(this, "No messaging app found", Toast.LENGTH_SHORT).show()
            }

            Log.v("TAG","SMS action done! ")

        }

        emailButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com")) // Thay thế "recipient@example.com" bằng địa chỉ email thực tế
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
                // Bạn cũng có thể đính kèm nhiều tệp bằng cách truyền một ArrayList của Uri
            }

            // Kiểm tra xem có ứng dụng nào có thể xử lý Intent không
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
                Log.v("TAG", "Send Email action done!")
            } else {
                Log.e("TAG", "No email app found!")
                // Xử lý khi không có ứng dụng email nào được tìm thấy
            }

            Log.v("TAG","Send Email action done! ")

        }

    }
}