package com.elitetechnologies.eventcalender.grid;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elitetechnologies.eventcalender.R;
import com.vrgsoft.calendar.VRCalendarCustomViewCallback;
import com.vrgsoft.calendar.VRCalendarView;
import com.vrgsoft.calendar.VrCalendarDay;
import com.vrgsoft.calendar.VrCalendarDaySettings;
import com.vrgsoft.calendar.calendar_listeners.OnCalendarClickListener;
import com.vrgsoft.calendar.calendar_listeners.OnCalendarLongClickListener;
import com.vrgsoft.calendar.calendar_listeners.VRCalendarMonthCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class GridCalenderActivity extends AppCompatActivity  implements OnCalendarClickListener, OnCalendarLongClickListener, VRCalendarMonthCallback {
    private VRCalendarView vrCalendarView;
    private DateFormat df = new SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_calender);

        vrCalendarView = findViewById(R.id.calendar);
        vrCalendarView.getSettings()
                .setOnCalendarClickListener(this)
                .setOnCalendarLongClickListener(this)
                .setVRCalendarMonthCallback(this);

        findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 2019);
                calendar.set(Calendar.MONTH, 6);
                calendar.set(Calendar.DAY_OF_MONTH, 27);
                vrCalendarView.moveToDate(new Date(calendar.getTimeInMillis()));
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 2015);
                calendar.set(Calendar.MONTH, 6);
                calendar.set(Calendar.DAY_OF_MONTH, 27);
                vrCalendarView.moveToDate(new Date(calendar.getTimeInMillis()));
            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrCalendarView.getSettings()
                        .setCurrentMonthBackgroundColor(Color.CYAN)
                        .updateCalendar();
            }
        });


        vrCalendarView.setDateFormat(df);
        vrCalendarView.weekContainer().setBackgroundColor(Color.parseColor("#b3ffd1"));
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.SATURDAY).setTextColor(Color.RED);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.SUNDAY).setTextColor(Color.RED);

        vrCalendarView.getDayOfWeekTextView(VRCalendarView.MONDAY).setTextSize(16f);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.TUESDAY).setTextSize(16f);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.WEDNESDAY).setTextSize(16f);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.THURSDAY).setTextSize(16f);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.FRIDAY).setTextSize(16f);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.SATURDAY).setTextSize(16f);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.SUNDAY).setTextSize(16f);

        vrCalendarView.getDayOfWeekTextView(VRCalendarView.MONDAY).setTypeface(null, Typeface.BOLD);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.TUESDAY).setTypeface(null, Typeface.BOLD);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.WEDNESDAY).setTypeface(null, Typeface.BOLD);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.THURSDAY).setTypeface(null, Typeface.BOLD);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.FRIDAY).setTypeface(null, Typeface.BOLD);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.SATURDAY).setTypeface(null, Typeface.BOLD);
        vrCalendarView.getDayOfWeekTextView(VRCalendarView.SUNDAY).setTypeface(null, Typeface.BOLD);
    }

    @Override
    public void onCalendarDayClick(VrCalendarDay day) {
        Toast.makeText(GridCalenderActivity.this, " " + df.format(day.getDate()), Toast.LENGTH_SHORT).show();

        VrCalendarDay today = new VrCalendarDay();
        today.setVRCalendarCustomViewCallback(new VRCalendarCustomViewCallback() {
            @Override
            public View getNewCustomiseView() {
                ImageView imageView = new ImageView(GridCalenderActivity.this);
                imageView.setImageResource(android.R.drawable.presence_online);
                return imageView;
            }
        });
        today.setDate(day.getDate());

        vrCalendarView.getSettings().updateCalendarDay(today, true);

    }

    @Override
    public void onCalendarDayLongClick(VrCalendarDay day) {
        Toast.makeText(GridCalenderActivity.this, "Long " + df.format(day.getDate()), Toast.LENGTH_SHORT).show();

        VrCalendarDay today = new VrCalendarDay();
        today.setDate(day.getDate());
        VrCalendarDaySettings vrCalendarDaySettings = new VrCalendarDaySettings();
        vrCalendarDaySettings.setDayTextStyle(VRCalendarView.BOLD);
        vrCalendarDaySettings.setDayBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        vrCalendarDaySettings.setDayTextColor(ContextCompat.getColor(this, R.color.colorToday));
        today.setVrCalendarDaySettings(vrCalendarDaySettings);

        vrCalendarView.getSettings().updateCalendarDay(today, false);
    }

    @Override
    public List<VrCalendarDay> getCustomizeDayView(Calendar calendar) {

        CustomDayUtils customDayUtils = new CustomDayUtils();

        return customDayUtils.getCustomizeDayView(this);
    }
}
