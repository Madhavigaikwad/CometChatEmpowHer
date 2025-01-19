package com.example.cometchatandroidsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chatuikit.conversations.CometChatConversations
import java.text.SimpleDateFormat
import java.util.*

class HealthActivity : AppCompatActivity() {

    private var waterCount = 0
    private val waterGoal = 8
    private val medicalHistory = mutableListOf<String>()
    private var periodStartDate: String? = null
    private var periodCycleLength = 28 // Default cycle length

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health)

        // Daily Health Tip
        val healthTipTextView: TextView = findViewById(R.id.tv_health_tip)
        val shareTipButton: Button = findViewById(R.id.btn_share_tip)

        val dailyTip = getDailyHealthTip()
        healthTipTextView.text = dailyTip

        shareTipButton.setOnClickListener {
            shareHealthTip(dailyTip)
        }

        // Medical History
        val medicalHistoryListView: ListView = findViewById(R.id.lv_medical_history)
        val addMedicalRecordButton: Button = findViewById(R.id.btn_add_medical_record)

        medicalHistoryListView.adapter = MedicalHistoryAdapter(this, medicalHistory)
        addMedicalRecordButton.setOnClickListener {
            addMedicalRecord()
        }

        // Emergency Contact
        val callContactButton: Button = findViewById(R.id.btn_call_contact)
        callContactButton.setOnClickListener {
            callEmergencyContact("+1234567890") // Replace with your contact
        }

        // Water Tracker
        val waterCountTextView: TextView = findViewById(R.id.tv_water_count)
        val addWaterButton: Button = findViewById(R.id.btn_add_water)

        updateWaterTracker(waterCountTextView)
        addWaterButton.setOnClickListener {
            if (waterCount < waterGoal) {
                waterCount++
                updateWaterTracker(waterCountTextView)
            } else {
                Toast.makeText(this, "You've reached your water goal!", Toast.LENGTH_SHORT).show()
            }
        }

        // Periods Tracker
        val logPeriodButton: Button = findViewById(R.id.btn_log_period)
        val periodPredictionTextView: TextView = findViewById(R.id.tv_period_prediction)

        updatePeriodPrediction(periodPredictionTextView)
        logPeriodButton.setOnClickListener {
            logPeriod(periodPredictionTextView)
        }
    }

    // Daily Health Tip
    private fun getDailyHealthTip(): String {
        val tips = listOf(
            "Drink plenty of water to stay hydrated.",
            "Take a 10-minute walk after meals.",
            "Include more fruits and vegetables in your diet.",
            "Get at least 7-8 hours of sleep daily.",
            "Practice deep breathing for 5 minutes every day."
        )
        val calendar = Calendar.getInstance()
        val dayIndex = calendar.get(Calendar.DAY_OF_YEAR) % tips.size
        return tips[dayIndex]
    }

    private fun shareHealthTip(tip: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, tip)
        startActivity(Intent.createChooser(intent, "Share via"))
    }

    // Medical History
    private fun addMedicalRecord() {
        medicalHistory.add("Sample record added on ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())}")
        (findViewById<ListView>(R.id.lv_medical_history).adapter as MedicalHistoryAdapter).notifyDataSetChanged()
    }

    // Emergency Contact
    private fun callEmergencyContact(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    // Water Tracker
    private fun updateWaterTracker(waterCountTextView: TextView) {
        waterCountTextView.text = "$waterCount/$waterGoal glasses"
    }

    // Periods Tracker
    private fun logPeriod(periodPredictionTextView: TextView) {
        periodStartDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        updatePeriodPrediction(periodPredictionTextView)
        Toast.makeText(this, "Period logged on $periodStartDate", Toast.LENGTH_SHORT).show()
    }

    private fun updatePeriodPrediction(periodPredictionTextView: TextView) {
        if (periodStartDate != null) {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val lastPeriodDate = sdf.parse(periodStartDate!!)
            val nextPeriodDate = Calendar.getInstance().apply {
                time = lastPeriodDate!!
                add(Calendar.DAY_OF_YEAR, periodCycleLength)
            }
            periodPredictionTextView.text = "Next period: ${sdf.format(nextPeriodDate.time)}"
        } else {
            periodPredictionTextView.text = "Next period: Not logged"
        }
    }
}
