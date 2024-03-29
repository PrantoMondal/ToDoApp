package com.studioartagonist.todoapppranto

import DatePickerDialogFragment
import TimePickerDialogFragment
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.studioartagonist.todoapppranto.databinding.FragmentNewToDoBinding
import com.studioartagonist.todoapppranto.entities.TodoModel
import com.studioartagonist.todoapppranto.prefdata.LoginPreference
import com.studioartagonist.todoapppranto.viewmodels.TodoViewModel

import getFormattedDateTime
import priority_normal
import java.util.*

class NewTodoFragment : Fragment() {
    private lateinit var binding: FragmentNewToDoBinding
    private val todoViewModel:TodoViewModel by viewModels()
    private lateinit var preference: LoginPreference
    var priority = priority_normal
    var dateInMillis = System.currentTimeMillis()
    var timeInMillis = System.currentTimeMillis()
    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0
    private var userId = 0L
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDays()
        preference = LoginPreference(requireContext())
        preference.userIdFlow.asLiveData()
            .observe(viewLifecycleOwner) {
                userId = it
            }
        binding = FragmentNewToDoBinding.inflate(inflater, container, false)
        binding.priorityRG.setOnCheckedChangeListener { group, checkedId ->
            val rb: RadioButton = group.findViewById(checkedId)
            priority = rb.text.toString()
        }
        binding.dateBtn.setOnClickListener {
            DatePickerDialogFragment { day, month, year, timestamp ->
                dateInMillis = timestamp
                this.day = day
                this.month = month
                this.year = year
                binding.dateTV.text = getFormattedDateTime(timestamp, "dd/MM/yyyy")
            }.show(childFragmentManager, "date_picker")

        }
        binding.timeBtn.setOnClickListener {
            TimePickerDialogFragment{hour, minute, it ->
                timeInMillis = it
                this.hour = hour
                this.minute = minute
                binding.timeTV.text = getFormattedDateTime(it, "hh:mm a")
            }.show(childFragmentManager, "time_picker")
        }
        binding.saveBtn.setOnClickListener {
            val name = binding.todoNameInputET.text.toString()
            val todo = TodoModel(
                name = name,
                userId = userId,
                priority = priority,
                date = dateInMillis,
                time = timeInMillis,
                day = day,
                month = month,
                year = year,
                hour = hour,
                minute = minute
            )
            todoViewModel.insertTodo(todo)
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun initDays() {
        val calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
    }
}