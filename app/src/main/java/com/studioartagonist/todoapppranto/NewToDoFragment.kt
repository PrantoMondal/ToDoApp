package com.studioartagonist.todoapppranto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioButton
import com.studioartagonist.todoapppranto.Customdialogs.DatePickerFragment
import com.studioartagonist.todoapppranto.Customdialogs.TimePickerFragment
import com.studioartagonist.todoapppranto.databinding.FragmentNewToDoBinding


class NewToDoFragment : Fragment() {
    private lateinit var binding: FragmentNewToDoBinding
    private var priorityType = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewToDoBinding.inflate(inflater, container, false)

        initPriorityTypeRadioGroup()

        binding.dateBtn.setOnClickListener {
            DatePickerFragment {
                binding.showDateTV.text = it
            }.show(childFragmentManager, null)

        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment {
                binding.showTimeTV.text = it
            }.show(childFragmentManager, null)

        }


        return binding.root
    }


    private fun initPriorityTypeRadioGroup() {


        binding.priorityTypeRG.setOnCheckedChangeListener { radioGroup, i ->
            var rb: RadioButton = radioGroup.findViewById(i)
            priorityType = rb.text.toString()
        }
    }

}
