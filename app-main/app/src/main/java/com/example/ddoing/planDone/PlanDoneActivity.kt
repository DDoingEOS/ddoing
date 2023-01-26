package com.example.ddoing.planDone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ddoing.databinding.ActivityPlanDoneBinding

class PlanDoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlanDoneBinding

    var ach : Int = 0 // 성취도에 따라 1~4로 들어감. 가장 좋은게 1
    var memo : String? = null
    var sharePlace : Int = 0 // 공유하면 1, 공유 안하면 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.planDoneButton.setOnClickListener {
            setVariable()

            //백엔드로 보내는 함수 만들기
            backEnd()

            //성공
            Toast.makeText(this, "계획 완료", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    fun backEnd() {
    }

    fun setVariable() {
        if(binding.sharePlace.isChecked) {
            sharePlace = 1
        }


        if(binding.radioButton1.isChecked) {
            ach = 1
        }
        if(binding.radioButton2.isChecked) {
            ach = 2
        }
        if(binding.radioButton3.isChecked) {
            ach = 3
        }
        if(binding.radioButton4.isChecked) {
            ach = 4
        }

        memo = binding.editTextMemo.text.toString()
    }
}