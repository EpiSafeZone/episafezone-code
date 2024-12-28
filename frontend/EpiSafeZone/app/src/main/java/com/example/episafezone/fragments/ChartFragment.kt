package com.example.episafezone.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.businesslogic.ChartLogic
import com.example.episafezone.databinding.FragmentChartsBinding
import com.example.episafezone.fragments.CalendarFragment.Companion
import com.example.episafezone.models.Patient
import com.example.episafezone.network.ChartPetitions
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.Locale

class ChartFragment() : Fragment(R.layout.fragment_charts) {
    private var _binding: FragmentChartsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChartsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingObj = binding
        ChartLogic.decorateCharts(binding)
        ChartLogic.getPieChart(patient)
        ChartLogic.getLineChart(patient)

        val gestureDetectorFragments = GestureDetector(this.getContext(), object : GestureDetector.SimpleOnGestureListener() {

            override fun onFling(
                e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float
            ): Boolean {
                if(e2.x - e1!!.x > 100 && Math.abs(velocityX) > 10){
                    MainActivity.changeToCalendar()
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

        binding.mainChart.setOnTouchListener{ v: View?, event: MotionEvent ->
            gestureDetectorFragments.onTouchEvent(event)
        }
        binding.mainChart.setOnClickListener{
            println("tocado")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private var patient = MainActivity.getPatient()
        private val contextObj = MainActivity.getContext()
        private lateinit var bindingObj : FragmentChartsBinding

        fun getContext() : Context {
            return contextObj
        }
        fun updatePatient(patient: Patient){
            this.patient = patient
        }

        fun setUpPieChart(pieList : List<String>){
            val count = pieList.groupingBy { it }.eachCount()

            val pieEntries = count.map { (key, value) ->
                PieEntry(value.toFloat(), key)
            }
            val epiWhite = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiWhite)
            val epiGreen = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiGreen)
            val pieDataSet = PieDataSet(pieEntries, "").apply {
                colors = listOf(
                    epiWhite,
                    Color.parseColor("#E7F5DE"),
                    Color.parseColor("#CAE8B8"),
                    Color.parseColor("#ADDC91"),
                    Color.parseColor("#90D06A"),
                )
                valueTextColor = Color.BLACK
                valueTextSize = 10f
            }
            val pieData = PieData(pieDataSet)

            bindingObj.pieChart.data = pieData
            bindingObj.pieChart.description.isEnabled = false
            bindingObj.pieChart.setEntryLabelColor(Color.BLACK)
            bindingObj.pieChart.animateY(1000)
            bindingObj.pieChart.invalidate()
        }

        fun setUpLineChart(lineMap : Map<String,Int>){
            // Convertir fechas a índices
            val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dates = lineMap.keys.toList()
            val entries = lineMap.entries.mapIndexed { index, entry ->
                Entry(index.toFloat(), entry.value.toFloat())
            }

            // Configurar el dataset
            val dataSet = LineDataSet(entries, "Fechas vs Números").apply {
                color = ContextCompat.getColor(ChartFragment.getContext(), R.color.epiGreen)
                valueTextColor = getColor(android.R.color.black)
                lineWidth = 2f
                setDrawCircles(false)
                setDrawCircleHole(false)
                setDrawValues(false)
            }

            // Formatear el eje X con las fechas
            bindingObj.lineChart.xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(dates)
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
            }

            // Configurar el gráfico
            bindingObj.lineChart.apply {
                data = LineData(dataSet)
                description.isEnabled = false
                animateX(1000)
            }
        }

    }
}