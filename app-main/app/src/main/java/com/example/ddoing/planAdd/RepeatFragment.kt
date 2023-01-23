package com.example.ddoing.planAdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ddoing.R
import com.example.ddoing.databinding.RepeatFragmentBinding


class RepeatFragment : Fragment() {

// 혹시 팝업창 띄우는 것을 프래그먼트 말고, https://yj95.tistory.com/30 이런 느낌으로 팝업창으로 만드는 것은 어떤가요?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var binding: RepeatFragmentBinding


        var r1 : Int = 0
        var r2 : Int = 0
        var r3 : Int = 0
        var r4 : Int = 0
        var r5 : Int = 0
        var r6 : Int = 0
        var r7 : Int = 0
        //https://roughroad.tistory.com/180
        //이 링크 보면 똑같은 작업을 해서 id 명만 바꾸어서 사용하면  Repeat_Date.xml이랑 똑같을 것 같아요
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
        binding.tableLayout.setOnClickListener{
            val planAddActivity = activity as PlanAdd
            planAddActivity.repeatReceiveData(r1, r2, r3, r4, r5, r6, r7)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.repeat_fragment, container, false)
    }

}