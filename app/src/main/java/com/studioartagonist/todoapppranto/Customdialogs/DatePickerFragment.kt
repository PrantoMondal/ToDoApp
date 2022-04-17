package com.studioartagonist.todoapppranto.Customdialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.actioninputes.getFormattedDateTime


class DatePickerFragment(val callback: (String) -> Unit)  : DialogFragment(),DatePickerDialog.OnDateSetListener{

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c= Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(),this,year,month,day)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val c = Calendar.getInstance()
        c.set(p1,p2,p3)
        val selectedDate = getFormattedDateTime(c.timeInMillis,"dd/MMM/yyyy")
        callback(selectedDate)
    }

}

class TimePickerFragment(val callback: (String) -> Unit) : DialogFragment(),TimePickerDialog.OnTimeSetListener{

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(requireActivity(),this,hour,minute,false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val t = Calendar.getInstance()
        t.set(0,0,0,p1,p2)
        val selectedTimer = getFormattedDateTime(t.timeInMillis,"h:mm a")
        callback(selectedTimer)
    }


}