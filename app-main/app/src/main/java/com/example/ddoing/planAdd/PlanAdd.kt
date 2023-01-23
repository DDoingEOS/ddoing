package com.example.ddoing.planAdd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ddoing.databinding.ActivityPlanAddBinding

class PlanAdd : AppCompatActivity() {
    lateinit var binding: ActivityPlanAddBinding

    var todoName: String? = null
    var memo: String? = null

    var isAlarm: Int = 0

    //시간, 날짜의 변수중 -1 인 것이 있으면 사용자가 작성하지 않은 것이 있는 것임
    //시간 time
    var startHour: Int = -1
    var startMinute: Int = 0

    var endHour: Int = -1
    var endMinute: Int = 0

    var alarmHour: Int = -1
    var alarmMinute: Int = 0

    //날짜 date
    var year: Int = -1 //현재 년도 그대로
    var month: Int = 0 // month는 0부터 시작
    var day: Int = 0

    //repeat
    var isRepeat: Int = 0
    var r1: Int = 0
    var r2: Int = 0
    var r3: Int = 0
    var r4: Int = 0
    var r5: Int = 0
    var r6: Int = 0
    var r7: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //시간, 날짜
        setTimeAndDateText()

        //제출버튼을 눌렀을때
        binding.planAddSubmit.setOnClickListener {
            SetVariable()

            if (checkAllInput()) {
                //백엔드로 보내는 함수 만들기
                Toast.makeText(this, "계획 추가 성공", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        //알림 설정
        binding.isAlert.setOnClickListener {
            if (binding.isAlert.isChecked()) {
                showTimePickerDialogAlarm()
            }
        }

        binding.isRepeat.setOnClickListener {
            if (binding.isRepeat.isChecked()) {
                showRepeatActivity()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            r1 = data?.getIntExtra("r1", 0)!!
            r2 = data?.getIntExtra("r2", 0)!!
            r3 = data?.getIntExtra("r3", 0)!!
            r4 = data?.getIntExtra("r4", 0)!!
            r5 = data?.getIntExtra("r5", 0)!!
            r6 = data?.getIntExtra("r6", 0)!!
            r7 = data?.getIntExtra("r7", 0)!!
        }
    }

    //시간 표기 String을 만들어준다.
    fun timeToString(hour: Int, minute: Int): String {
        if (hour == -1) {
            return "-"
        }

        var hour = hour

        var AmOrPm = "오전"

        if (hour >= 12) {
            AmOrPm = "오후"
        }
        if (hour >= 13) {
            hour = hour - 12
        }

        return AmOrPm + " " + "%02d".format(hour) + ":" + "%02d".format(minute) + " ▼"
    }

    fun dateToString(year: Int, month: Int, day: Int): String {
        if (year == -1) {
            return "-"
        }
        var year = year
        var month = month
        year = year - 2000
        month++

        return "" + year + "." + "%02d".format(month) + "." + "%02d".format(day) + " ▼"
    }


    //시간, 날짜를 버튼의 text에 넣는다.
    fun setTimeAndDateText() {
        binding.timeStart.text = "시작 시각: " + timeToString(startHour, startMinute)
        binding.timeEnd.text = "끝 시각: " + timeToString(endHour, endMinute)
        binding.date.text = "날짜: " + "\n" + dateToString(year, month, day)
    }

    //사용자의 input에 문제가 없는지 확인
    fun checkAllInput(): Boolean {
        //필수 입력 변수들 중 하나라도 입력 안한게 있으면 제출불가
        if (todoName == "" || startHour == -1 || endHour == -1 || (isAlarm == 1 && alarmHour == -1) || year == -1) {
            Toast.makeText(this, "입력되지 않은 필수항목이 있습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        //끝 시간이 시작시간보다 빠를때
        if ((startHour > endHour) || (startHour == endHour && startMinute > endMinute)) {
            Toast.makeText(this, "끝 시간이 시작시간보다 빠릅니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    //사용자가 입력한 정보들을 변수에 저장
    fun SetVariable() {
        todoName = binding.todoName.text.toString()
        memo = binding.memo.text.toString()

        if (binding.isRepeat.isChecked()) {
            isRepeat = 1
        }
        if (binding.isAlert.isChecked()) {
            isAlarm = 1
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
    fun showRepeatActivity() {
        val intent = Intent(this, RepeatActivity::class.java)
        startActivityForResult(intent, 99)
    }

    //timePicker에서 시간설정 완료됐을때 시간 받아오기
    fun timePickerReceiveData(hour: Int, minute: Int, time: String?) {
        if (time.equals("start")) {
            this.startHour = hour
            this.startMinute = minute
        } else if (time.equals("end")) {
            this.endHour = hour
            this.endMinute = minute
        } else if (time.equals("alarm")) {
            this.alarmHour = hour
            this.alarmMinute = minute
        }
        this.setTimeAndDateText()
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
        this.setTimeAndDateText()
    }

}