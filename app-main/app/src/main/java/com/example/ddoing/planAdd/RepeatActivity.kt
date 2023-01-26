package com.example.ddoing.planAdd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ddoing.databinding.ActivityRepeatBinding

class RepeatActivity : AppCompatActivity() {
    lateinit var binding: ActivityRepeatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepeatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var r1 : Int = 0
        var r2 : Int = 0
        var r3 : Int = 0
        var r4 : Int = 0
        var r5 : Int = 0
        var r6 : Int = 0
        var r7 : Int = 0

        binding.planDoneButton.setOnClickListener{
            if(binding.checkBox1.isChecked()) {
                r1 = 1
            }
            if(binding.checkBox2.isChecked()) {
                r2 = 1
            }
            if(binding.checkBox3.isChecked()) {
                r3 = 1
            }
            if(binding.checkBox4.isChecked()) {
                r4 = 1
            }
            if(binding.checkBox5.isChecked()) {
                r5 = 1
            }
            if(binding.checkBox6.isChecked()) {
                r6 = 1
            }
            if(binding.checkBox7.isChecked()) {
                r7 = 1
            }

            val intent = Intent(this, PlanAdd::class.java)
            intent.putExtra("r1", r1)
            intent.putExtra("r2", r2)
            intent.putExtra("r3", r3)
            intent.putExtra("r4", r4)
            intent.putExtra("r5", r5)
            intent.putExtra("r6", r6)
            intent.putExtra("r7", r7)

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}