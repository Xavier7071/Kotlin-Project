package com.example.project.Views

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

/*class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

          fun onCreateDialog(savedInstanceState: Bundle): Dialog {
            // Use the current time as the default values for the picker
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
             return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
        }

        override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
            // Do something with the time chosen by the user
        }
}*/