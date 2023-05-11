package com.example.myapplication.for_editing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentConfirmationBinding
import com.example.myapplication.DataModel

class ConfirmationFragment : Fragment() {
    lateinit var binding: FragmentConfirmationBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bCancelF.setOnClickListener {
            binding.con.visibility = View.GONE
            dataModel.delete.value = false
        }
        binding.bDeleteF.setOnClickListener {
            dataModel.delete.value = true
            binding.con.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConfirmationFragment()
    }
}