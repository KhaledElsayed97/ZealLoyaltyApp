package com.khaleddev.zealapp.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khaleddev.zealapp.core.BroadcastConstants.TRANSACTION_DATA
import com.khaleddev.zealapp.core.BroadcastConstants.TRANSACTION_INTENT_ACTION


class ZealBroadcastReceiver() : BroadcastReceiver() {

    var transactionAmount: String? = null

    private val _data = MutableLiveData<String>()

    // Public LiveData that can be observed from the UI
    val data: LiveData<String> get() = _data

    // Function to update the data
    fun updateData(newData: String) {
        _data.value = newData
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            TRANSACTION_INTENT_ACTION -> {
                transactionAmount = intent?.getStringExtra(TRANSACTION_DATA)
                transactionAmount?.let { updateData(it) }
            }
        }
    }
}