package com.workfort.calender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements CalendarPickerView.OnDateSelectedListener{
    private TextView checkInTv, checkOutTv, nightCountTv;
    private CalendarPickerView calendarPickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);
        checkInTv = (TextView) findViewById(R.id.check_in_tv);
        checkOutTv = (TextView) findViewById(R.id.check_out_tv);
        nightCountTv = (TextView) findViewById(R.id.night_count_tv);
        calendarPickerView = (CalendarPickerView) findViewById(R.id.calendar_view);
        calendarPickerView.setOnDateSelectedListener(this);

        List<String> list = new ArrayList<>();
        list.add("2018-09-11");
        list.add("2018-09-15");
        list.add("2018-09-16");
        list.add("2018-09-18");
        calendarPickerView.setInactiveDate(list);

        checkInTv.setText("");
        checkOutTv.setText("");
        checkOutTv.setHint("---");
        nightCountTv.setText(String.format(getString(R.string.nights_count), 0));
        calendarPickerView.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 2);
        calendarPickerView.init(new Date(), nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
    }

    @Override
    public void onDateSelected(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        List<Date> selectedDateList = calendarPickerView.getSelectedDates();

        if(selectedDateList.size() > 1){
            checkInTv.setText(dateFormat.format(selectedDateList.get(0)));
            checkOutTv.setText(dateFormat.format(selectedDateList.get(selectedDateList.size() - 1)));
        }else {
            checkInTv.setText(dateFormat.format(date));
            checkOutTv.setHint(getString(R.string.select_date));
        }

    }

    @Override
    public void onDateUnselected(Date date) {

    }
}
