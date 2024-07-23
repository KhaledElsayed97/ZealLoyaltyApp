package com.khaleddev.zealapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.khaleddev.zealapp.MainActivity
import com.khaleddev.zealapp.R
import com.khaleddev.zealapp.core.BroadcastConstants.FINAL_TRANSACTION_DATA
import com.khaleddev.zealapp.core.BroadcastConstants.FINAL_TRANSACTION_INTENT_ACTION
import com.khaleddev.zealapp.databinding.FragmentDiscountManagementBinding


class DiscountManagerFragment : Fragment() {

    private var binding: FragmentDiscountManagementBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscountManagementBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (activity as? MainActivity)?.zealBroadcastReceiver?.data?.observe(viewLifecycleOwner, Observer { transactionAmount ->
                Toast.makeText(
                    requireContext(),
                    "Received transaction details",
                    Toast.LENGTH_SHORT
                ).show()
                tvTransactionAmount.text = transactionAmount
                tvTotalAmount.text = transactionAmount
            })
            tvTotalAmount.text = tvTransactionAmount.text
            btnDiscount.setOnClickListener {
                if (etDiscountAmount.text.isEmpty())
                    Toast.makeText(
                        requireContext(),
                        "Please enter a discount amount",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    val transactionAmount = tvTransactionAmount.text.toString().toFloat()
                    val discountAmount = etDiscountAmount.text.toString().toFloat()
                    var totalAmount = transactionAmount - discountAmount
                    if (totalAmount < 0) totalAmount = 0f
                    tvTotalAmount.text = totalAmount.toString()
                }
            }
            btnProceed.setOnClickListener {
                sendFinalTransaction(tvTotalAmount.text.toString())
                findNavController().navigate(R.id.action_discountManagerFragment_to_successFragment)
            }
        }
    }

    private fun sendFinalTransaction(finalTransactionAmount: String) {
        val intent = Intent(FINAL_TRANSACTION_INTENT_ACTION)
        intent.putExtra(
            FINAL_TRANSACTION_DATA,
            finalTransactionAmount
        )
        activity?.sendBroadcast(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}



