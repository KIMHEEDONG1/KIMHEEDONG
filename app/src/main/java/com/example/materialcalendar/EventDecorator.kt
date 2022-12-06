package com.example.materialcalendar

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class EventDecorator(context: Context, date: CalendarDay): DayViewDecorator{
    private var date = date
    val drawable = context.getDrawable(R.drawable.calendar_date_selector)
    override fun shouldDecorate(day: CalendarDay?): Boolean{
        return day?.equals(date)!!
    }
    override fun decorate(view: DayViewFacade?) {
        if(drawable != null) {
            view?.setSelectionDrawable(drawable)
        }
        else{
            Log.d("event_decorator","is null")
        }
    }
}