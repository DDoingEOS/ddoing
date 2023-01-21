package com.example.ddoing.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ddoing.MainActivity
import com.example.ddoing.databinding.ActivitySignUpBinding
import com.example.ddoing.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        //Submit 버튼이 눌릴때 signUpSubmit 함수 실행
        binding.signUpSubmit.setOnClickListener {
            createUser()
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
    }

    fun createUser() {
        val uid = auth.currentUser?.uid.orEmpty()
        val name = binding.userName.text.toString()
        val currentDB = FirebaseDatabase.getInstance().reference.child("users").child(uid)
        val userMap = mutableMapOf<String, Any>()
        userMap["uid"] = uid
        userMap["name"] = name
        currentDB.updateChildren(userMap)
    }

}