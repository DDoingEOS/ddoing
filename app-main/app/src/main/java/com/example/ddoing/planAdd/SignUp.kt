package com.example.ddoing.planAdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ddoing.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    //입력받을 정보들의 변수
    var name : String? = null
    var email : String? = null
    var password : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Submit 버튼이 눌릴때 signUpSubmit 함수 실행
        binding.signUpSubmit.setOnClickListener {
            SetVariable()
        }
    }

    //사용자가 입력한 정보들을 변수에 저장
    fun SetVariable() {
        name = binding.userName.text.toString()
    }
}