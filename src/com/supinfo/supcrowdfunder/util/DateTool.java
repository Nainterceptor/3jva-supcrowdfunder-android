package com.supinfo.supcrowdfunder.util;

import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Robin on 17/12/13.
 */
public class DateTool {
    public static String calendarString(Calendar date) {
        return date.get(Calendar.YEAR) + "-" +
                ((date.get(Calendar.MONTH) < 9) ? "0" + (date.get(Calendar.MONTH) + 1) : (date.get(Calendar.MONTH) + 1)) + "-" +
                ((date.get(Calendar.DAY_OF_MONTH) < 10) ? "0" + date.get(Calendar.DAY_OF_MONTH) :
                        date.get(Calendar.DAY_OF_MONTH));
    }

    public static String datePickerString(DatePicker datePicker) {
        return datePicker.getYear() + "-" +
                ((datePicker.getMonth() < 9) ? "0" + (datePicker.getMonth() + 1) : (datePicker.getMonth() + 1)) + "-" +
                ((datePicker.getDayOfMonth() < 9) ? "0" + (datePicker.getDayOfMonth()) : (datePicker.getDayOfMonth()));
    }
}
