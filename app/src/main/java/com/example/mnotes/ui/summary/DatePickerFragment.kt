package com.example.mnotes.ui.summary

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.mnotes.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private val calendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Get the initial date values from the calendar
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog with the initial date values and set the listener
        val datePickerDialog = DatePickerDialog(requireActivity(), R.style.CustomDatePickerDialog, this, year, month, day)

        // Set the initial date on the DatePickerDialog
        val selectedDate =
            arguments?.getString("SELECTED_DATE")
                ?.let { SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(it) }
        selectedDate?.let {
            calendar.time = it
            datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        }

        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(calendar.time)

        val selectedDateBundle = Bundle()
        selectedDateBundle.putString("SELECTED_DATE", selectedDate)

        setFragmentResult("REQUEST_KEY", selectedDateBundle)
    }
}