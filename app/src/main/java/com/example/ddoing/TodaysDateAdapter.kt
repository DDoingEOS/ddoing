package com.example.ddoing;

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the RecyclerView with today plan in HomeFragment
 */

class TodaysDateAdapter :
    RecyclerView.Adapter<TodaysDateAdapter.TodaysDateViewHolder>(){

    // 우선은 random data (이름, 시간, 해당 일정 PK 받아옴)
    private val tempa = Date("1111", "인공지능", "12:30")
    private val tempb = Date("2222", "형베시", "12:00")
    private val tempc = Date("3333", "운형체제", "16:00")
    private val tempd = Date("4444", "에오스 홈커밍", "18:00")
    private val tempe = Date("5555", "강아지랑 약속", "19:00")
    private val tempf = Date("6666", "방정리", "11:00")
    private val tempg = Date("7777", "불닭챌린지", "21:00")
    private val temph = Date("8888", "말랑말랑 젤리", "21:00")
    private val tempi = Date("9999", "엽떡먹기", "14:30")
    private val list = mutableListOf<Date>(tempa, tempb, tempc, tempd, tempe, tempf, tempg, temph, tempi)


    class TodaysDateViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.homewview_now_date_name)
        val time = view.findViewById<TextView>(R.id.homeview_time_remain)
        val button = view.findViewById<Button>(R.id.homeview_date_now_start)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaysDateViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.homeview_todays_date_item, parent, false)

        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = Accessibility
        return TodaysDateViewHolder(layout)
    }

    // 각 view 데이터 설정
    override fun onBindViewHolder(holder: TodaysDateViewHolder, position: Int) {
        val item = list.get(position)
        holder.name.text = item.todoName
        holder.time.text = item.alertTime

        // 확인 누르면 어떤일 일어나게 할것인지
        holder.button.setOnClickListener {

        }
    }

    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = "test"
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }
}
