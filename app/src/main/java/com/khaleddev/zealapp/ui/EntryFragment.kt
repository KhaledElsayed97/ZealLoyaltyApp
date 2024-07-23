package com.khaleddev.zealapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khaleddev.zealapp.R
import com.khaleddev.zealapp.databinding.FragmentEntryBinding

class EntryFragment : Fragment(){

    private var binding: FragmentEntryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnStart?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_discountManagerFragment)
        }
    }
}