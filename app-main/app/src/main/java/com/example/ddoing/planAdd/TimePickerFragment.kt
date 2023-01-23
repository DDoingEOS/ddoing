package com.example.ddoing.planAdd

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        // https://all-dev-kang.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-timepickerdialog-spinner-%EB%AA%A8%EB%93%9C%EB%A1%9C-%EB%B3%80%EA%B2%BD%ED%95%98%EB%8A%94-%EB%B2%95
        // 위 링크를 통해서 timepicker sipnner 모드로 만들면 더 이쁠 것 같아요.
        //return TimePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_NoActionBa,this, hour, minute, DateFormat.is24HourFormat(activity));

        return TimePickerDialog(activity,this, hour, minute, DateFormat.is24HourFormat(activity));

    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        var time: String? = null
        arguments?.let {
            time = it.getString("time")
        }

        val planAddActivity = activity as PlanAdd
        planAddActivity.timePickerReceiveData(hourOfDay, minute, time)
    }
}