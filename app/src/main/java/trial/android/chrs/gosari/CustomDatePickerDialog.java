package trial.android.chrs.gosari;

import android.app.DatePickerDialog;
import android.os.Build;
import android.content.Context;
import java.util.Calendar;
import android.widget.DatePicker;

/**
 * Created by greg on 5/7/16.
 */
public class CustomDatePickerDialog extends DatePickerDialog {

    int maxYear;
    int maxMonth;
    int maxDay;

    public CustomDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public void setMaxDate(long maxDate) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getDatePicker().setMaxDate(System.currentTimeMillis());
        } else {
            final Calendar c = Calendar.getInstance();
            c.setTimeInMillis(maxDate);
            maxYear = c.get(Calendar.YEAR);
            maxMonth = c.get(Calendar.MONTH);
            maxDay = c.get(Calendar.DAY_OF_MONTH);
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.onDateChanged(view, year, monthOfYear, dayOfMonth);
        } else {
            if (year > maxYear)
                view.updateDate(maxYear, maxMonth, maxDay);

            if (monthOfYear > maxMonth && year == maxYear)
                view.updateDate(maxYear, maxMonth, maxDay);

            if (dayOfMonth > maxDay && year == maxYear && monthOfYear == maxMonth)
                view.updateDate(maxYear, maxMonth, maxDay);
        }
    }
}
