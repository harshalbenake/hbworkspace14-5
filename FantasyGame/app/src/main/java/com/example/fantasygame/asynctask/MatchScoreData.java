package com.example.fantasygame.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.ArrayMap;
import android.util.Log;

import com.example.fantasygame.R;
import com.example.fantasygame.fragments.MatchScoreFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * This async task is used for Match Score Service
 */
public class MatchScoreData extends AsyncTask<String, String, String> {
    Context mContext;
    Fragment mFragment;
    String mSelectedtext;
    private ArrayMap<String, String> mArrScoreDetails = new ArrayMap<>();
    private ArrayList<ArrayMap<String, String>> mArrSummaryList = new ArrayList<>();

    public MatchScoreData(Context context, Fragment fragment, String selectedtext) {
        this.mContext = context;
        this.mFragment = fragment;
        this.mSelectedtext = selectedtext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        if (!isCancelled()) {
            try {
                mArrScoreDetails = new ArrayMap<>();
                mArrSummaryList = new ArrayList<>();
                String strResponse = getOverData(mSelectedtext);
                JSONObject jsonObject = new JSONObject(strResponse);
                JSONObject miniscore = jsonObject.optJSONObject("miniscore");
                String curOvsStats = miniscore.optString("curOvsStats");
                String lastWkt = miniscore.optString("lastWkt");
                JSONArray performance = miniscore.optJSONArray("performance");
                if(performance!=null) {
                    for (int i = 0; i < performance.length(); i++) {
                        JSONObject performanceIndex = performance.optJSONObject(i);
                        String runs = performanceIndex.optString("runs");
                        String wickets = performanceIndex.optString("wickets");
                        String label = performanceIndex.optString("label");
                        mArrScoreDetails.put("performance" + i, label + ": " + runs + " runs, " + wickets + " wkts");
                    }
                }

                JSONObject batsmanStriker = miniscore.optJSONObject("batsmanStriker");
                String batsmanStrikerName = batsmanStriker.optString("name");
                String batsmanStrikerRuns = batsmanStriker.optString("runs");
                String batsmanStrikerBalls = batsmanStriker.optString("balls");
                String batsmanStrikerFours = batsmanStriker.optString("fours");
                String batsmanStrikerSixes = batsmanStriker.optString("sixes");
                String batsmanStrikerStrkRate = batsmanStriker.optString("strkRate");

                JSONObject batsmanNonStriker = miniscore.optJSONObject("batsmanNonStriker");
                String batsmanNonStrikerName = batsmanNonStriker.optString("name");
                String batsmanNonStrikerRuns = batsmanNonStriker.optString("runs");
                String batsmanNonStrikerBalls = batsmanNonStriker.optString("balls");
                String batsmanNonStrikerFours = batsmanNonStriker.optString("fours");
                String batsmanNonStrikerSixes = batsmanNonStriker.optString("sixes");
                String batsmanNonStrikerStrkRate = batsmanNonStriker.optString("strkRate");

                JSONObject bowlerStriker = miniscore.optJSONObject("bowlerStriker");
                String bowlerStrikerName = bowlerStriker.optString("name");
                String bowlerStrikerOvers = bowlerStriker.optString("overs");
                String bowlerStrikerMaidens = bowlerStriker.optString("maidens");
                String bowlerStrikerRuns = bowlerStriker.optString("runs");
                String bowlerStrikerWickets = bowlerStriker.optString("wickets");
                String bowlerStrikerEconomy = bowlerStriker.optString("economy");

                JSONObject bowlerNonStriker = miniscore.optJSONObject("bowlerNonStriker");
                String bowlerNonStrikerName = bowlerNonStriker.optString("name");
                String bowlerNonStrikerOvers = bowlerNonStriker.optString("overs");
                String bowlerNonStrikerMaidens = bowlerNonStriker.optString("maidens");
                String bowlerNonStrikerRuns = bowlerNonStriker.optString("runs");
                String bowlerNonStrikerWickets = bowlerNonStriker.optString("wickets");
                String bowlerNonStrikerEconomy = bowlerNonStriker.optString("economy");

                JSONObject matchHeaders = jsonObject.optJSONObject("matchHeaders");
                String state = matchHeaders.optString("state");
                String status = matchHeaders.optString("status");

                JSONArray overSepList = jsonObject.optJSONArray("overSepList");
                for (int i = 0; i < overSepList.length(); i++) {
                    JSONObject overSepListIndex = overSepList.optJSONObject(i);
                    JSONArray overSep=overSepListIndex.optJSONArray("overSep");
                    if(overSep!=null){
                        for (int j = 0; j < overSep.length(); j++) {
                            ArrayMap<String, String> arrOverSummaryDetails = new ArrayMap<>();

                            JSONObject overSepIndex = overSep.optJSONObject(j);
                            String overSummary = overSepIndex.optString("overSummary");
                            String runs = overSepIndex.optString("runs");
                            String wickets = overSepIndex.optString("wickets");
                            String score = overSepIndex.optString("score");
                            String overNum = overSepIndex.optString("overNum");
                            arrOverSummaryDetails.put("overSummary",overSummary);
                            arrOverSummaryDetails.put("runs",runs);
                            arrOverSummaryDetails.put("wickets",wickets);
                            arrOverSummaryDetails.put("score",score);
                            arrOverSummaryDetails.put("overNum",overNum);
                            mArrSummaryList.add(arrOverSummaryDetails);
                        }
                    }
                }
                mArrScoreDetails.put("state",state);
                mArrScoreDetails.put("status",status);

                mArrScoreDetails.put("curOvsStats",curOvsStats);
                mArrScoreDetails.put("lastWkt",lastWkt);

                mArrScoreDetails.put("batsmanStrikerName",batsmanStrikerName);
                mArrScoreDetails.put("batsmanStrikerRuns",batsmanStrikerRuns);
                mArrScoreDetails.put("batsmanStrikerBalls",batsmanStrikerBalls);
                mArrScoreDetails.put("batsmanStrikerFours",batsmanStrikerFours);
                mArrScoreDetails.put("batsmanStrikerSixes",batsmanStrikerSixes);
                mArrScoreDetails.put("batsmanStrikerStrkRate",batsmanStrikerStrkRate);

                mArrScoreDetails.put("batsmanNonStrikerName",batsmanNonStrikerName);
                mArrScoreDetails.put("batsmanNonStrikerRuns",batsmanNonStrikerRuns);
                mArrScoreDetails.put("batsmanNonStrikerBalls",batsmanNonStrikerBalls);
                mArrScoreDetails.put("batsmanNonStrikerFours",batsmanNonStrikerFours);
                mArrScoreDetails.put("batsmanNonStrikerSixes",batsmanNonStrikerSixes);
                mArrScoreDetails.put("batsmanNonStrikerStrkRate",batsmanNonStrikerStrkRate);

                mArrScoreDetails.put("bowlerStrikerName",bowlerStrikerName);
                mArrScoreDetails.put("bowlerStrikerOvers",bowlerStrikerOvers);
                mArrScoreDetails.put("bowlerStrikerMaidens",bowlerStrikerMaidens);
                mArrScoreDetails.put("bowlerStrikerRuns",bowlerStrikerRuns);
                mArrScoreDetails.put("bowlerStrikerWickets",bowlerStrikerWickets);
                mArrScoreDetails.put("bowlerStrikerEconomy",bowlerStrikerEconomy);

                mArrScoreDetails.put("bowlerNonStrikerName",bowlerNonStrikerName);
                mArrScoreDetails.put("bowlerNonStrikerOvers",bowlerNonStrikerOvers);
                mArrScoreDetails.put("bowlerNonStrikerMaidens",bowlerNonStrikerMaidens);
                mArrScoreDetails.put("bowlerNonStrikerRuns",bowlerNonStrikerRuns);
                mArrScoreDetails.put("bowlerNonStrikerWickets",bowlerNonStrikerWickets);
                mArrScoreDetails.put("bowlerNonStrikerEconomy",bowlerNonStrikerEconomy);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mFragment instanceof MatchScoreFragment) {
            ((MatchScoreFragment) mFragment).setMatchScoreDetails(mArrScoreDetails,mArrSummaryList);
        }
    }

    private String getOverData(String matchId) {
        String strResponse = "";
        try {
            Request request = new Request.Builder()
                    .url("https://unofficial-cricbuzz.p.rapidapi.com/matches/get-overs?matchId=" + matchId)
                    .get()
                    .addHeader("x-rapidapi-host", "unofficial-cricbuzz.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", mContext.getString(R.string.cricbuzz_key))
                    .build();
            OkHttpClient.Builder builder = new OkHttpClient.Builder().protocols(Arrays.asList(Protocol.HTTP_1_1));
            builder.connectTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES);
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("HttpLogging", message);
                }
            });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .protocols(Arrays.asList(Protocol.HTTP_1_1))
                    .addInterceptor(logging)
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            System.out.println("response: " + response);
            strResponse = response.body().string().replaceAll("\\r\\n", "");
            System.out.println("OkHttp strResponse: " + strResponse);
            return strResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResponse;
    }
}
