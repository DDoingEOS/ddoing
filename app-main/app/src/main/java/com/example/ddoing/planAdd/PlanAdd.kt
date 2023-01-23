package com.example.ddoing.planAdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ddoing.R
import com.example.ddoing.databinding.ActivityPlanAddBinding

class PlanAdd : AppCompatActivity() {
    lateinit var binding: ActivityPlanAddBinding

    var todoName : String? = null
    var memo : String? = null

    var isAlert : Int = 0

    //시간, 날짜의 변수중 -1 인 것이 있으면 사용자가 작성하지 않은 것이 있는 것임
    //시간 time
    var startHour : Int = -1
    var startMinute : Int = 0

    var endHour : Int = -1
    var endMinute : Int = 0

    var alarmHour : Int = -1
    var alarmMinute : Int = 0

    //날짜 date
    var year : Int = -1
    var month : Int = 0
    var day : Int = 0

    //repeat
    var isRepeat : Int = 0
    var r1 : Int = 0
    var r2 : Int = 0
    var r3 : Int = 0
    var r4 : Int = 0
    var r5 : Int = 0
    var r6 : Int = 0
    var r7 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //제출버튼을 눌렀을때
        binding.planAddSubmit.setOnClickListener {
            SetVariable()

            if(checkAllInput()){
                //백엔드로 보내는 함수 만들기
            }
        }
//        binding.button1.setOnClickListener{
//            if(binding.button1.isPressed == true) {
//                showDatePickerDialog()
//            }
//        }

        //알림 설정
        binding.isAlert.setOnClickListener {
            if(binding.isAlert.isChecked()) {
                showTimePickerDialogAlarm()
            }
        }

        binding.isRepeat.setOnClickListener {
            if(binding.isRepeat.isChecked()) {
                showRepeatFragment()
            }
        }
    }

    //필수 입력 변수들 중 하나라도 입력 안한게 있으면 제출불가
    fun checkAllInput() :Boolean {
        return !(todoName == null || startHour == -1 || endHour == -1 || alarmHour == -1 || year == -1)
    }


    //사용자가 입력한 정보들을 변수에 저장
    fun SetVariable() {
        todoName = binding.todoName.text.toString()
        memo = binding.memo.text.toString()

        if(binding.isRepeat.isChecked()) {
            isRepeat = 1
        }
        if(binding.isAlert.isChecked()) {
            isAlert = 1
        }

        //시간, 날짜의 변수중 값이 -1 인것 있으면 제출불가
    }

    //시간 버튼을 눌렀을때 timepicker 실행
    fun showTimePickerDialogStart(v: View) {
        var fragment = TimePickerFragment()
        var bundle = Bundle()
        bundle.putString("time", "start")
        fragment.arguments = bundle
        fragment.show(supportFragmentManager, "timePicker")
    }
    fun showTimePickerDialogEnd(v: View) {
        var fragment = TimePickerFragment()
        var bundle = Bundle()
        bundle.putString("time", "end")
        fragment.arguments = bundle
        fragment.show(supportFragmentManager, "timePicker")
    }
    fun showTimePickerDialogAlarm() {
        var fragment = TimePickerFragment()
        var bundle = Bundle()
        bundle.putString("time", "alarm")
        fragment.arguments = bundle
        fragment.show(supportFragmentManager, "timePicker")
    }
    //반복
    fun showRepeatFragment() {
    }

    //timePicker에서 시간설정 완료됐을때 시간 받아오기
    fun timePickerReceiveData(hour: Int, minute: Int, time: String?) {
        if(time.equals("start")) {
            this.startHour = hour
            this.startMinute = minute
        }
        else if(time.equals("end")) {
            this.endHour = hour
            this.endMinute = minute
        }
        else if(time.equals("alarm")) {
            this.alarmHour = hour
            this.alarmMinute = minute
        }
    }

    //datepicker
    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun datePickerReceiveData(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
    }

    //repeatFragment에서 정보 받아오기
    fun repeatReceiveData(r1: Int, r2: Int, r3: Int, r4: Int, r5: Int, r6: Int, r7: Int) {
        this.r1 = r1
        this.r2 = r2
        this.r3 = r3
        this.r4 = r4
        this.r5 = r5
        this.r6 = r6
        this.r7 = r7
    }

}