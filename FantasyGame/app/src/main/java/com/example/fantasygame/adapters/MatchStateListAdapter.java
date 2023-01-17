package com.example.fantasygame.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fantasygame.R;
import com.example.fantasygame.utils.HelperUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MatchStateListAdapter extends BaseAdapter {

    private Activity mContext;
    private ArrayList<ArrayMap<String, String>> mArrMatchState;

    public MatchStateListAdapter(Activity context, ArrayList<ArrayMap<String, String>> arrMatchState) {
        this.mContext = context;
        this.mArrMatchState = arrMatchState;
    }

    @Override
    public int getCount() {
        return mArrMatchState.size();
    }

    @Override
    public Object getItem(int i) {
        return mArrMatchState.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ArrayMap<String, String> arrSeriesDetails = mArrMatchState.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.rowitem_cardview_item, null);
        }

        String matchType = arrSeriesDetails.get("matchType");
        String matchId = arrSeriesDetails.get("matchId");
        String seriesId = arrSeriesDetails.get("seriesId");
        String seriesName = arrSeriesDetails.get("seriesName");
        String matchDesc = arrSeriesDetails.get("matchDesc");
        String matchFormat = arrSeriesDetails.get("matchFormat");
        String venueInfo = arrSeriesDetails.get("venueInfo");
        String team1 = arrSeriesDetails.get("team1");
        String team2 = arrSeriesDetails.get("team2");


        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        TextView text2 = (TextView) convertView.findViewById(R.id.text2);
        text2.setVisibility(View.VISIBLE);

        text1.setText(matchType + "\n" +
                "Series: " + seriesName + "\n\n" +
                team1 + " vs " + team2);
        text1.setTypeface(Typeface.DEFAULT_BOLD);
        text2.setText("Venue:" + venueInfo + " | Desc: " + matchDesc + " | Format: " + matchFormat);
        return convertView;
    }

}