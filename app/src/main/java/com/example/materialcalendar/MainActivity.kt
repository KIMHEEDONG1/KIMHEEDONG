package com.example.materialcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendar()
    }

    fun calendar(){
        val materialCalendar : MaterialCalendarView = findViewById(R.id.calendar)
        var startTimeCalendar = Calendar.getInstance()
        var endTimeCalendar = Calendar.getInstance()

        val currentYear = startTimeCalendar.get(Calendar.YEAR)
        val currentMonth = startTimeCalendar.get(Calendar.MONTH)
        val currentDay = startTimeCalendar.get(Calendar.DATE)

        // 달력 설정
        materialCalendar.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY) // 일 월 화 수 목 금 토
            .setMinimumDate(CalendarDay.from(1980, 1, 1))
            .setMaximumDate(CalendarDay.from(2200, 12, 31))
            .commit()

        // 최대 날짜 설정
        val enCalendarDay = CalendarDay(
            endTimeCalendar.get(Calendar.YEAR),
            endTimeCalendar.get(Calendar.MONTH),
            endTimeCalendar.get(Calendar.DATE)
        )

        //val maxDecorator = MaxDecorator(enCalendarDay)
        val saturdayDecorator = SaturdayDecorator()
        val sundayDecorator = SundayDecorator()
        val eventDecorator = EventDecorator(this)
        val todayDecorator = TodayDecorator()


        materialCalendar.addDecorators(
            saturdayDecorator, sundayDecorator, todayDecorator, eventDecorator
        )

        // 처음 달력 title format
        materialCalendar.setTitleFormatter(TitleFormatter {
            val simpleDateFormat = SimpleDateFormat("yyyy년 MM월", Locale.KOREA)
            simpleDateFormat.format(startTimeCalendar.time)
        })

        // 달력 넘길때 format
        materialCalendar.setOnMonthChangedListener { widget, date ->
            materialCalendar.setTitleFormatter(TitleFormatter {
                val simpleDateFormat = SimpleDateFormat("yyyy년 MM월", Locale.KOREA)
                simpleDateFormat.format(date.date)
            })
        }
    }
}


