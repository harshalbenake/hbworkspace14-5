package com.elitetechnologies.eventcalender.grid;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.elitetechnologies.eventcalender.R;
import com.vrgsoft.calendar.VRCalendarCustomViewCallback;
import com.vrgsoft.calendar.VRCalendarView;
import com.vrgsoft.calendar.VrCalendarDay;
import com.vrgsoft.calendar.VrCalendarDaySettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomDayUtils {

    public List<VrCalendarDay> getCustomizeDayView(final Context context) {
        List<VrCalendarDay> vrCalendarDays = new ArrayList<>();
        vrCalendarDays.add(getToday(context));
        vrCalendarDays.add(getEventDay(context));
        return vrCalendarDays;
    }

    private VrCalendarDay getToday(final Context context) {
        VrCalendarDay today = new VrCalendarDay();
        today.setDate(new Date());
        VrCalendarDaySettings todaySettings = new VrCalendarDaySettings();
        today.setVrCalendarDaySettings(todaySettings);
        today.setVRCalendarCustomViewCallback(new VRCalendarCustomViewCallback() {
            @Override
            public View getNewCustomiseView() {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(android.R.drawable.presence_online);
                return imageView;
            }
        });
        return today;
    }

    private VrCalendarDay getEventDay(final Context context) {
        VrCalendarDay tomorrow = new VrCalendarDay();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        tomorrow.setDate(d);
        tomorrow.setVRCalendarCustomViewCallback(new VRCalendarCustomViewCallback() {
            @Override
            public View getNewCustomiseView() {
                CustomView customView = new CustomView(context);
                return customView;
            }
        });
        VrCalendarDaySettings vrtomorCalendarDaySettings = new VrCalendarDaySettings();
        vrtomorCalendarDaySettings.setDayTextStyle(VRCalendarView.BOLD);
        vrtomorCalendarDaySettings.setDayTextColor(ContextCompat.getColor(context, R.color.colorDark));
        tomorrow.setVrCalendarDaySettings(vrtomorCalendarDaySettings);

        return tomorrow;
    }

    private VrCalendarDay getSomeDay(final Context context) {

        VrCalendarDay someDay = new VrCalendarDay();

        VrCalendarDaySettings someDaySettings = new VrCalendarDaySettings();
        someDaySettings.setDayTextColor(Color.CYAN);
        someDay.setVrCalendarDaySettings(someDaySettings);
        someDay.setVRCalendarCustomViewCallback(new VRCalendarCustomViewCallback() {
            @Override
            public View getNewCustomiseView() {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(android.R.drawable.presence_online);
                return imageView;
            }
        });

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5);
        someDay.setDate(cal.getTime());
        someDay.setVrCalendarDaySettings(someDaySettings);
        return someDay;
    }

    private VrCalendarDay getSomeDayAgain(final Context context) {

        VrCalendarDay someDay = new VrCalendarDay();

        VrCalendarDaySettings someDaySettings = new VrCalendarDaySettings();
        someDaySettings.setDayTextColor(Color.TRANSPARENT);
        someDaySettings.setDayBackgroundColor(Color.TRANSPARENT);
        someDay.setVrCalendarDaySettings(someDaySettings);
        someDay.setVRCalendarCustomViewCallback(new VRCalendarCustomViewCallback() {
            @Override
            public View getNewCustomiseView() {

                ImageView imageView = new ImageView(context);
                imageView.setImageResource(android.R.drawable.presence_online);

                return imageView;
            }
        });

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -6);
        someDay.setDate(cal.getTime());
        someDay.setVrCalendarDaySettings(someDaySettings);
        return someDay;
    }

    private VrCalendarDay getDay1(final Context context) {
        VrCalendarDay today = new VrCalendarDay();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        today.setDate(cal.getTime());
        VrCalendarDaySettings todaySettings = new VrCalendarDaySettings();
        todaySettings.setDayTextColor(Color.GRAY);
        today.setVrCalendarDaySettings(todaySettings);
        return today;
    }

    private VrCalendarDay getDay0(final Context context) {
        VrCalendarDay today = new VrCalendarDay();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 8);
        today.setDate(cal.getTime());
        VrCalendarDaySettings todaySettings = new VrCalendarDaySettings();
        todaySettings.setDayTextColor(Color.WHITE);
        todaySettings.setDayTextSize(10);
        today.setVrCalendarDaySettings(todaySettings);
        return today;
    }

    private VrCalendarDay getDay2(final Context context) {
        VrCalendarDay today = new VrCalendarDay();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 6);
        today.setDate(cal.getTime());
        VrCalendarDaySettings todaySettings = new VrCalendarDaySettings();
        todaySettings.setDayTextColor(Color.GREEN);
        todaySettings.setDayBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.background_sample));
        today.setVrCalendarDaySettings(todaySettings);
        return today;
    }

    private VrCalendarDay getDay3(final Context context) {
        VrCalendarDay today = new VrCalendarDay();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 4);
        today.setDate(cal.getTime());
        VrCalendarDaySettings todaySettings = new VrCalendarDaySettings();
        todaySettings.setDayTextColor(Color.BLUE);
        today.setVrCalendarDaySettings(todaySettings);
        return today;
    }


}