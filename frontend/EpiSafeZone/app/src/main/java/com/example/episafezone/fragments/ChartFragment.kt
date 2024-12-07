package com.example.episafezone.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.episafezone.R
import com.example.episafezone.databinding.FragmentChartsBinding
import com.example.episafezone.models.Patient
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class ChartFragment(val patient: Patient, val pieList : List<String>) : Fragment(R.layout.fragment_charts) {
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
        setUpPieChart()
        setUpLineChart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpPieChart(){
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

    }
}