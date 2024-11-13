package com.example.episafezone.decorator

import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class DayDecoratorYellow(
    private val colorMap: Map<CalendarDay, Int>
) : DayViewDecorator {


    override fun shouldDecorate(day: CalendarDay): Boolean {
        return colorMap.containsKey(day)
    }

    override fun decorate(view: DayViewFacade) {
        var color: Int

        for (date in colorMap.keys) {
            if (shouldDecorate(date)) {
                color = colorMap[date]!!
                view.addSpan(ForegroundColorSpan(color))
                view.addSpan(DotSpan(8f, color))
            }
        }
    }
}