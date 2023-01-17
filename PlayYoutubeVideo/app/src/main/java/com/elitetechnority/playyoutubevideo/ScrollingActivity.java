package com.elitetechnority.playyoutubevideo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initLayout();
    }

    private void initLayout() {
        WebView mwv_youtube = (WebView) findViewById(R.id.wv_youtube);
        WebView mwv_youtube1 = (WebView) findViewById(R.id.wv_youtube1);
        WebView mwv_youtube2 = (WebView) findViewById(R.id.wv_youtube2);
        WebView mwv_youtube3 = (WebView) findViewById(R.id.wv_youtube3);

        setYouTubeFrame(mwv_youtube, "pjMX9OEb_4k");
        setYouTubeFrame(mwv_youtube1, "yPaz8ODLsSw");
        setYouTubeFrame(mwv_youtube2, "K9Jh2Moagv8");
        setYouTubeFrame(mwv_youtube3, "o4dzyHD8oXY");
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setYouTubeFrame(WebView webView, String strYouTubeId) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        String videoStr = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + strYouTubeId + "?controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay=1; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
        webView.loadData(videoStr, "text/html", "utf-8");
    }

}