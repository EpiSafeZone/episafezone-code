package com.example.episafezone.decorator

import android.graphics.drawable.GradientDrawable
import com.prolificinteractive.materialcalendarview.DayViewDecorator

import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewFacade

class GeneralDayDecorator(
    private val backgroundColor: Int,
    private val textColor: Int
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade) {
        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.OVAL // Cambia a RECTANGLE si quieres fondo cuadrado
            setColor(backgroundColor) // Fondo
        }

        view.setBackgroundDrawable(drawable) // Establece el fondo
        view.addSpan(ForegroundColorSpan(textColor))
    }


}