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
import priority_normal


class NewToDoFragment : Fragment() {
    private lateinit var binding: FragmentNewToDoBinding
    var priority = priority_normal
    var dateInMillis = System.currentTimeMillis()
    var timeInMillis = System.currentTimeMillis()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewToDoBinding.inflate(inflater, container, false)

        binding.priorityRG.setOnCheckedChangeListener { group, checkedId ->
            val rb: RadioButton = group.findViewById(checkedId)
            priority = rb.text.toString()
        }

        binding.dateBtn.setOnClickListener {
            DatePickerFragment {
                binding.dateTV.text = it
            }.show(childFragmentManager, null)

        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment {
                binding.timeTV.text = it
            }.show(childFragmentManager, null)

        }


        return binding.root
    }
}
