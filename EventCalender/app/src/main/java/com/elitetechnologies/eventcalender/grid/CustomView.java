package com.elitetechnologies.eventcalender.grid;


import android.content.Context;
import android.widget.RelativeLayout;

import com.elitetechnologies.eventcalender.R;

public class CustomView extends RelativeLayout {

    public CustomView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.custom_view, this);
    }
}