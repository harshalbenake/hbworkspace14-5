package com.elitetechnologies.eventcalender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.elitetechnologies.eventcalender.grid.GridCalenderActivity;
import com.elitetechnologies.eventcalender.horizontal.HorizontalCalenderActivity;
import com.elitetechnologies.eventcalender.vertical.VerticalCalenderActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_horizontalcalender=(Button)findViewById(R.id.btn_horizontalcalender);
        Button btn_verticalcalender=(Button)findViewById(R.id.btn_verticalcalender);
        Button btn_gridcalender=(Button)findViewById(R.id.btn_gridcalender);
        btn_horizontalcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HorizontalCalenderActivity.class));
            }
        });
        btn_verticalcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,VerticalCalenderActivity.class));
            }
        });


        btn_gridcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GridCalenderActivity.class));
            }
        });


    }
}
