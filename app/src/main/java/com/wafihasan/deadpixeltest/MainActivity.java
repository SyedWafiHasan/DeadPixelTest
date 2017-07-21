package com.wafihasan.deadpixeltest;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
{
    InterstitialAd ad;
    String[] clr = {"#000000", "#FFFFFF", "#F44336", "#E91E63", "#102372",
            "#9C27B0", "#673AB7", "#3F51B5", "#009688",
            "#2196F3", "#4CAF50", "#00BCD4", "#FFEB3B", "#FFC107", "#FF9800",
            "#FF5722", "#795548", "#9E9E9E", "#607D8B", "#FFFFFF"};
    int sz = clr.length;
    int co = 0;
    RelativeLayout rl;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        textview = (TextView) findViewById(R.id.tv);
        rl = (RelativeLayout) findViewById(R.id.activity_main);

        ad = new InterstitialAd(this);
        ad.setAdUnitId("ca-app-pub-2397811954559570/2578500840");

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }

        rl.setOnClickListener(new View.OnClickListener()
        {
        public void onClick(View rl)
        {
            textview.setText("");
            rl.setBackgroundColor(Color.parseColor(clr[co]));
            co += 1;
            if (co == sz)
            {
                co = 0;
                textview.setText("Click to go again.");
                ad.loadAd(new AdRequest.Builder().build());
                ad.show();
            }
        }
        }
        );
    }
    //App ID: ca-app-pub-2397811954559570~9943174911
    //Ad unit ID: ca-app-pub-2397811954559570/2578500840
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        this.finish();
    }
}