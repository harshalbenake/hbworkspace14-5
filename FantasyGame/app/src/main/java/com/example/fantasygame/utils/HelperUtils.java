package com.example.fantasygame.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.fantasygame.R;

import java.util.List;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class HelperUtils {
    Context mContext;
    public HelperUtils(Context context) {
        this.mContext=context;
    }

    public static boolean isNullOrEmpty(String val) {
        boolean result = false;
        try {
            if (val == null || val.equals("") || val.equalsIgnoreCase("null") || val.equals(".") || val.trim().length() == 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getSumListData(List<Double> doubleList){
        double sum = 0;
        for(int i = 0; i < doubleList.size(); i++)
            sum += doubleList.get(i);
        return sum;
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
    }

    public void setNotification(){
        String CHANNEL_ID="00001";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon( R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(001, builder.build());
    }
}
