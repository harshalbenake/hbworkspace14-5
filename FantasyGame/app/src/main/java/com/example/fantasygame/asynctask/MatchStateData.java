package com.example.fantasygame.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.ArrayMap;
import android.util.Log;

import com.example.fantasygame.R;
import com.example.fantasygame.fragments.HomeFragment;

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
 * This async task is used for Match State Service
 */
public class MatchStateData extends AsyncTask<String, String, String> {
    Context mContext;
    Fragment mFragment;
    String mSelectedtext;
    ArrayList<ArrayMap<String, String>> mArrMatchState = new ArrayList<>();
    public MatchStateData(Context context, Fragment fragment, String selectedtext) {
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
                String strResponse = getMatchStateData(mSelectedtext);
                JSONObject jsonObject = new JSONObject(strResponse);
                JSONArray typeMatches = jsonObject.optJSONArray("typeMatches");
                for (int i = 0; i < typeMatches.length(); i++) {
                    JSONObject typeMatchesIndex = typeMatches.optJSONObject(i);
                    String matchType = typeMatchesIndex.optString("matchType");
                    JSONArray seriesAdWrapper = typeMatchesIndex.optJSONArray("seriesAdWrapper");
                    for (int j = 0; j < seriesAdWrapper.length(); j++) {
                        JSONObject seriesAdWrapperIndex = seriesAdWrapper.optJSONObject(j);
                        if (seriesAdWrapperIndex.has("seriesMatches")) {
                            JSONObject seriesMatches = seriesAdWrapperIndex.optJSONObject("seriesMatches");
                            JSONArray matches = seriesMatches.optJSONArray("matches");
                            for (int k = 0; k < matches.length(); k++) {
                                JSONObject matchesIndex = matches.optJSONObject(k);
                                JSONObject matchInfo = matchesIndex.optJSONObject("matchInfo");

                                int matchId = matchInfo.optInt("matchId");
                                int seriesId = matchInfo.optInt("seriesId");
                                String seriesName = matchInfo.optString("seriesName");
                                String matchDesc = matchInfo.optString("matchDesc");
                                String matchFormat = matchInfo.optString("matchFormat");
                                JSONObject venueInfo = matchInfo.getJSONObject("venueInfo");
                                String ground = venueInfo.optString("ground");
                                String city = venueInfo.optString("city");
                                JSONObject team1 = matchInfo.getJSONObject("team1");
                                String teamName1 = team1.optString("teamName");
                                String imageId1 = team1.optString("imageId");
                                JSONObject team2 = matchInfo.getJSONObject("team2");
                                String teamName2 = team2.optString("teamName");
                                String imageId2 = team2.optString("imageId");

                                System.out.println("seriesName: " + seriesName + " " + seriesId);
                                ArrayMap arrSeriesDetails = new ArrayMap<>();
                                arrSeriesDetails.put("matchType", matchType);
                                arrSeriesDetails.put("matchId", matchId + "");
                                arrSeriesDetails.put("seriesId", seriesId + "");
                                arrSeriesDetails.put("seriesName", seriesName);
                                arrSeriesDetails.put("matchDesc", matchDesc);
                                arrSeriesDetails.put("matchFormat", matchFormat);
                                arrSeriesDetails.put("venueInfo", ground + ", " + city);
                                arrSeriesDetails.put("team1", teamName1);
                                arrSeriesDetails.put("team2", teamName2);

                                mArrMatchState.add(arrSeriesDetails);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mFragment instanceof HomeFragment) {
            ((HomeFragment) mFragment).setMatchStateAdapter(mArrMatchState);
        }
    }

    private String getMatchStateData(String strMatchState) {
        String strResponse = "";
        try {
            Request request = new Request.Builder()
                    .url("https://unofficial-cricbuzz.p.rapidapi.com/matches/list?matchState=" + strMatchState.toLowerCase())
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
