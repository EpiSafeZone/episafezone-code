package com.example.episafezone.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ChartPetitions.initializeQueue()
        setUpPieChart()
        setUpLineChart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setUpPieChart(){
        val pieList = ChartLogic.processPieResponse()
        val count = pieList.groupingBy { it }.eachCount()

        val pieEntries = count.map { (key, value) ->
            PieEntry(value.toFloat(), key)
        }

        val pieDataSet = PieDataSet(pieEntries, "Manifestaciones").apply {
            colors = listOf(
                Color.BLUE,
                Color.RED,
                Color.GREEN,
                Color.MAGENTA,
                Color.YELLOW
            )
            valueTextColor = Color.BLACK
            valueTextSize = 14f
        }
        val pieData = PieData(pieDataSet)
        binding.pieChart.data = pieData

        binding.pieChart.description.isEnabled = false
        binding.pieChart.centerText = "Frecuencia de manifestaciones"
        binding.pieChart.setEntryLabelColor(Color.BLACK)
        binding.pieChart.animateY(1000)
        binding.pieChart.invalidate()
    }

    private fun setUpLineChart(){
        val lineMap = ChartLogic.getLineChart(patient)

        // Convertir fechas a índices
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dates = lineMap.keys.toList()
        val entries = lineMap.entries.mapIndexed { index, entry ->
            Entry(index.toFloat(), entry.value.toFloat())
        }

        // Configurar el dataset
        val dataSet = LineDataSet(entries, "Fechas vs Números").apply {
            color = getColor(android.R.color.holo_blue_dark)
            valueTextColor = getColor(android.R.color.black)
            lineWidth = 2f
            circleRadius = 4f
            setDrawCircleHole(true)
        }

        // Formatear el eje X con las fechas
        binding.lineChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(dates)
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
        }

        // Configurar el gráfico
        binding.lineChart.apply {
            data = LineData(dataSet)
            description.text = "Gráfico de Fechas"
            animateX(1000)
        }
    }

    companion object{
        private var patient = MainActivity.getPatient()
        private val contextObj = MainActivity.getContext()

        fun getContext() : Context {
            return contextObj
        }

        fun updatePatient(patient: Patient){
            this.patient = patient
        }
    }
}