package com.example.ddoing.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ddoing.MainActivity
import com.example.ddoing.databinding.ActivityLoginBinding
import com.example.ddoing.planAdd.PlanAdd

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //구글 로그인
        binding.loginWithGoogle.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }

        //회원가입
        binding.signUp.setOnClickListener {
            val nextIntent = Intent(this, SignUp::class.java)
            startActivity(nextIntent)
        }

        //계획추가
        binding.login.setOnClickListener {
            val nextIntent = Intent(this, PlanAdd::class.java)
            startActivity(nextIntent)
        }
    }
}