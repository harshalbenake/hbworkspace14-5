package com.example.fantasygame.fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fantasygame.R;
import com.example.fantasygame.asynctask.MatchScoreData;
import com.example.fantasygame.utils.HelperUtils;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class MatchScoreFragment extends Fragment {
    private LinearLayout mcontainer_matchscore;
    private TextView mtv_curovsstats,mtv_lastwkt,mtv_last5overs,mtv_last10overs;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_matchscore, container, false);
        mtv_curovsstats = root.findViewById(R.id.tv_curovsstats);
        mtv_curovsstats.setTypeface(Typeface.DEFAULT_BOLD);
        mtv_lastwkt = root.findViewById(R.id.tv_lastwkt);
        mtv_last5overs = root.findViewById(R.id.tv_last5overs);
        mtv_last10overs = root.findViewById(R.id.tv_last10overs);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            final String matchId = bundle.getString("matchId");
            if (!HelperUtils.isNullOrEmpty(matchId)) {
                new MatchScoreData(getActivity(), MatchScoreFragment.this, matchId).execute();


              /*  final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new MatchScoreData(getActivity(), MatchScoreDetailsFragment.this, matchId).execute();
                        handler.postDelayed(this,20000);
                    }
                },20000);*/
            } else {
                Toast.makeText(getActivity(), "Invalid match id", Toast.LENGTH_SHORT).show();
            }
        }

        mcontainer_matchscore = (LinearLayout) root.findViewById(R.id.container_matchscore);

        return root;
    }



    public void setMatchScoreDetails(ArrayMap<String, String> arrScoreDetails, ArrayList<ArrayMap<String, String>> arrSummaryList) {
        String strState=arrScoreDetails.get("state");
        String strCurOvsStats = arrScoreDetails.get("curOvsStats");
        if(!HelperUtils.isNullOrEmpty(strState) && strState.equalsIgnoreCase("Complete")){
            mtv_curovsstats.setText(arrScoreDetails.get("status"));
        }else{
            if(!HelperUtils.isNullOrEmpty(strCurOvsStats)) {
                mtv_curovsstats.setText("Recent: " + strCurOvsStats);
                mtv_lastwkt.setText("Last Wkt: " + arrScoreDetails.get("lastWkt"));
            }else{
                mtv_curovsstats.setText("Starting in few mins....");
            }
        }
        mtv_last5overs.setText(arrScoreDetails.get("performance0"));
        mtv_last10overs.setText(arrScoreDetails.get("performance1"));

        mcontainer_matchscore.removeAllViews();

        View batterTitleView = getLayoutInflater().inflate(R.layout.rowitem_scoreboard, null);
        TextView tv_batter_title = (TextView) batterTitleView.findViewById(R.id.tv_score_title);
        TextView tv_batter_value1 = (TextView) batterTitleView.findViewById(R.id.tv_score_value1);
        TextView tv_batter_value2 = (TextView) batterTitleView.findViewById(R.id.tv_score_value2);
        TextView tv_batter_value3 = (TextView) batterTitleView.findViewById(R.id.tv_score_value3);
        TextView tv_batter_value4 = (TextView) batterTitleView.findViewById(R.id.tv_score_value4);
        TextView tv_batter_value5 = (TextView) batterTitleView.findViewById(R.id.tv_score_value5);

        tv_batter_title.setText("Batter");
        tv_batter_value1.setText("R");
        tv_batter_value2.setText("B");
        tv_batter_value3.setText("4s");
        tv_batter_value4.setText("6s");
        tv_batter_value5.setText("SR");

        tv_batter_title.setTypeface(Typeface.DEFAULT_BOLD);
        tv_batter_value1.setTypeface(Typeface.DEFAULT_BOLD);
        tv_batter_value2.setTypeface(Typeface.DEFAULT_BOLD);
        tv_batter_value3.setTypeface(Typeface.DEFAULT_BOLD);
        tv_batter_value4.setTypeface(Typeface.DEFAULT_BOLD);
        tv_batter_value5.setTypeface(Typeface.DEFAULT_BOLD);

        batterTitleView.setPadding(10,10,10,10);
        batterTitleView.setBackgroundColor(Color.parseColor("#E3E6E3"));
        if(!HelperUtils.isNullOrEmpty(strCurOvsStats)) {
            mcontainer_matchscore.addView(batterTitleView);
        }

        View player1TitleView = getLayoutInflater().inflate(R.layout.rowitem_scoreboard, null);
        TextView tv_player1_title = (TextView) player1TitleView.findViewById(R.id.tv_score_title);
        TextView tv_player1_value1 = (TextView) player1TitleView.findViewById(R.id.tv_score_value1);
        TextView tv_player1_value2 = (TextView) player1TitleView.findViewById(R.id.tv_score_value2);
        TextView tv_player1_value3 = (TextView) player1TitleView.findViewById(R.id.tv_score_value3);
        TextView tv_player1_value4 = (TextView) player1TitleView.findViewById(R.id.tv_score_value4);
        TextView tv_player1_value5 = (TextView) player1TitleView.findViewById(R.id.tv_score_value5);

        tv_player1_title.setText(arrScoreDetails.get("batsmanStrikerName"));
        tv_player1_value1.setText(arrScoreDetails.get("batsmanStrikerBalls"));
        tv_player1_value2.setText(arrScoreDetails.get("batsmanStrikerRuns"));
        tv_player1_value3.setText(arrScoreDetails.get("batsmanStrikerFours"));
        tv_player1_value4.setText(arrScoreDetails.get("batsmanStrikerSixes"));
        tv_player1_value5.setText(arrScoreDetails.get("batsmanStrikerStrkRate"));

        mcontainer_matchscore.addView(player1TitleView);

        View player2TitleView = getLayoutInflater().inflate(R.layout.rowitem_scoreboard, null);
        TextView tv_player2_title = (TextView) player2TitleView.findViewById(R.id.tv_score_title);
        TextView tv_player2_value1 = (TextView) player2TitleView.findViewById(R.id.tv_score_value1);
        TextView tv_player2_value2 = (TextView) player2TitleView.findViewById(R.id.tv_score_value2);
        TextView tv_player2_value3 = (TextView) player2TitleView.findViewById(R.id.tv_score_value3);
        TextView tv_player2_value4 = (TextView) player2TitleView.findViewById(R.id.tv_score_value4);
        TextView tv_player2_value5 = (TextView) player2TitleView.findViewById(R.id.tv_score_value5);

        tv_player2_title.setText(arrScoreDetails.get("batsmanNonStrikerName"));
        tv_player2_value1.setText(arrScoreDetails.get("batsmanNonStrikerBalls"));
        tv_player2_value2.setText(arrScoreDetails.get("batsmanNonStrikerRuns"));
        tv_player2_value3.setText(arrScoreDetails.get("batsmanNonStrikerFours"));
        tv_player2_value4.setText(arrScoreDetails.get("batsmanNonStrikerSixes"));
        tv_player2_value5.setText(arrScoreDetails.get("batsmanNonStrikerStrkRate"));

        mcontainer_matchscore.addView(player2TitleView);

        View bowlerTitleView = getLayoutInflater().inflate(R.layout.rowitem_scoreboard, null);
        TextView tv_bowler_title = (TextView) bowlerTitleView.findViewById(R.id.tv_score_title);
        TextView tv_bowler_value1 = (TextView) bowlerTitleView.findViewById(R.id.tv_score_value1);
        TextView tv_bowler_value2 = (TextView) bowlerTitleView.findViewById(R.id.tv_score_value2);
        TextView tv_bowler_value3 = (TextView) bowlerTitleView.findViewById(R.id.tv_score_value3);
        TextView tv_bowler_value4 = (TextView) bowlerTitleView.findViewById(R.id.tv_score_value4);
        TextView tv_bowler_value5 = (TextView) bowlerTitleView.findViewById(R.id.tv_score_value5);

        tv_bowler_title.setText("Bowler");
        tv_bowler_value1.setText("O");
        tv_bowler_value2.setText("M");
        tv_bowler_value3.setText("R");
        tv_bowler_value4.setText("W");
        tv_bowler_value5.setText("ECO");

        tv_bowler_title.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bowler_value1.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bowler_value2.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bowler_value3.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bowler_value4.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bowler_value5.setTypeface(Typeface.DEFAULT_BOLD);

        bowlerTitleView.setPadding(10,10,10,10);
        bowlerTitleView.setBackgroundColor(Color.parseColor("#E3E6E3"));
        if(!HelperUtils.isNullOrEmpty(strCurOvsStats)) {
            mcontainer_matchscore.addView(bowlerTitleView);
        }

        View player3TitleView = getLayoutInflater().inflate(R.layout.rowitem_scoreboard, null);
        TextView tv_player3_title = (TextView) player3TitleView.findViewById(R.id.tv_score_title);
        TextView tv_player3_value1 = (TextView) player3TitleView.findViewById(R.id.tv_score_value1);
        TextView tv_player3_value2 = (TextView) player3TitleView.findViewById(R.id.tv_score_value2);
        TextView tv_player3_value3 = (TextView) player3TitleView.findViewById(R.id.tv_score_value3);
        TextView tv_player3_value4 = (TextView) player3TitleView.findViewById(R.id.tv_score_value4);
        TextView tv_player3_value5 = (TextView) player3TitleView.findViewById(R.id.tv_score_value5);

        tv_player3_title.setText(arrScoreDetails.get("bowlerStrikerName"));
        tv_player3_value1.setText(arrScoreDetails.get("bowlerStrikerOvers"));
        tv_player3_value2.setText(arrScoreDetails.get("bowlerStrikerMaidens"));
        tv_player3_value3.setText(arrScoreDetails.get("bowlerStrikerRuns"));
        tv_player3_value4.setText(arrScoreDetails.get("bowlerStrikerWickets"));
        tv_player3_value5.setText(arrScoreDetails.get("bowlerStrikerEconomy"));

        mcontainer_matchscore.addView(player3TitleView);

        View player4TitleView = getLayoutInflater().inflate(R.layout.rowitem_scoreboard, null);
        TextView tv_player4_title = (TextView) player4TitleView.findViewById(R.id.tv_score_title);
        TextView tv_player4_value1 = (TextView) player4TitleView.findViewById(R.id.tv_score_value1);
        TextView tv_player4_value2 = (TextView) player4TitleView.findViewById(R.id.tv_score_value2);
        TextView tv_player4_value3 = (TextView) player4TitleView.findViewById(R.id.tv_score_value3);
        TextView tv_player4_value4 = (TextView) player4TitleView.findViewById(R.id.tv_score_value4);
        TextView tv_player4_value5 = (TextView) player4TitleView.findViewById(R.id.tv_score_value5);

        tv_player4_title.setText(arrScoreDetails.get("bowlerNonStrikerName"));
        tv_player4_value1.setText(arrScoreDetails.get("bowlerNonStrikerOvers"));
        tv_player4_value2.setText(arrScoreDetails.get("bowlerNonStrikerMaidens"));
        tv_player4_value3.setText(arrScoreDetails.get("bowlerNonStrikerRuns"));
        tv_player4_value4.setText(arrScoreDetails.get("bowlerNonStrikerWickets"));
        tv_player4_value5.setText(arrScoreDetails.get("bowlerNonStrikerEconomy"));

        mcontainer_matchscore.addView(player4TitleView);
    }
}