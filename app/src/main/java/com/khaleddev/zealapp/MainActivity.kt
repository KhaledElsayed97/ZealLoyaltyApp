package com.khaleddev.zealapp

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.khaleddev.zealapp.core.BroadcastConstants.TRANSACTION_INTENT_ACTION
import com.khaleddev.zealapp.core.ZealBroadcastReceiver

class MainActivity : AppCompatActivity() {
    var zealBroadcastReceiver = ZealBroadcastReceiver()
    private var transactionAmountFilter = IntentFilter(TRANSACTION_INTENT_ACTION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(zealBroadcastReceiver,transactionAmountFilter)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}