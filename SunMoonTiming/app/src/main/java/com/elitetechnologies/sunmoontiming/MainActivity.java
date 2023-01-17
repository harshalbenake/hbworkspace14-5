package com.elitetechnologies.sunmoontiming;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.util.Log;



import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends Activity implements LocationListener {
    protected LocationManager locationManager;
    private TextView tv_daytime_time1;
    private TextView tv_daytime_time2;
    private TextView tv_daytime_time3;
    private TextView tv_daytime_time4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        View layout_daytime1 = (View) findViewById( R.id.layout_daytime1 );
        View layout_daytime2 = (View) findViewById( R.id.layout_daytime2 );
        View layout_daytime3 = (View) findViewById( R.id.layout_daytime3 );
        View layout_daytime4 = (View) findViewById( R.id.layout_daytime4 );

        TextView tv_daytime_title1 = (TextView) layout_daytime1.findViewById( R.id.tv_daytime_title );
        TextView tv_daytime_title2 = (TextView) layout_daytime2.findViewById( R.id.tv_daytime_title );
        TextView tv_daytime_title3 = (TextView) layout_daytime3.findViewById( R.id.tv_daytime_title );
        TextView tv_daytime_title4 = (TextView) layout_daytime4.findViewById( R.id.tv_daytime_title );

        tv_daytime_title1.setText( "Sunrise" );
        tv_daytime_title2.setText( "Sunset" );
        tv_daytime_title3.setText( "Moonrise" );
        tv_daytime_title4.setText( "Moonset" );

        ImageView iv_daytime1 = (ImageView) layout_daytime1.findViewById( R.id.iv_daytime );
        ImageView iv_daytime2 = (ImageView) layout_daytime2.findViewById( R.id.iv_daytime );
        ImageView iv_daytime3 = (ImageView) layout_daytime3.findViewById( R.id.iv_daytime );
        ImageView iv_daytime4 = (ImageView) layout_daytime4.findViewById( R.id.iv_daytime );

        iv_daytime1.setImageResource( R.drawable.ic_sunrise );
        iv_daytime2.setImageResource( R.drawable.ic_sunset );
        iv_daytime3.setImageResource( R.drawable.ic_moonrise );
        iv_daytime4.setImageResource( R.drawable.ic_moonset );

        tv_daytime_time1 = (TextView) layout_daytime1.findViewById( R.id.tv_daytime_time );
        tv_daytime_time2 = (TextView) layout_daytime2.findViewById( R.id.tv_daytime_time );
        tv_daytime_time3 = (TextView) layout_daytime3.findViewById( R.id.tv_daytime_time );
        tv_daytime_time4 = (TextView) layout_daytime4.findViewById( R.id.tv_daytime_time );

        final Animation anim_rotate = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.anim_rotate );
        iv_daytime1.setAnimation( anim_rotate );
        iv_daytime1.startAnimation( anim_rotate );

        final Animation anim_zoominout = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.anim_zoominout );
        iv_daytime2.setAnimation( anim_zoominout );
        iv_daytime2.startAnimation( anim_zoominout );
        iv_daytime4.setAnimation( anim_zoominout );
        iv_daytime4.startAnimation( anim_zoominout );
    }


    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        try {
            Calendar calendar = Calendar.getInstance();
            SunTimes sunTimes = SunTimes.compute()
                    .on( calendar.getTime() )   // set a date
                    .at( location.getLatitude(), location.getLongitude() )   // set a location
                    .execute();     // get the results
            MoonTimes moonTimes = MoonTimes.compute()
                    .on( calendar.getTime() )   // set a date
                    .at( location.getLatitude(), location.getLongitude() )   // set a location
                    .execute();     // get the results
            tv_daytime_time1.setText( "" + new SimpleDateFormat( "K:mm a" ).format( sunTimes.getRise() ) );
            tv_daytime_time2.setText( "" + new SimpleDateFormat( "K:mm a" ).format( sunTimes.getSet() ) );
            tv_daytime_time3.setText( "" + new SimpleDateFormat( "K:mm a" ).format( moonTimes.getRise() ) );
            tv_daytime_time4.setText( "" + new SimpleDateFormat( "K:mm a" ).format( moonTimes.getSet() ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}
