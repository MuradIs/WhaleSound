package com.muradismayilov.martiandeveloper.whalesound;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    AdView adView;

    ImageView imageView;
    TextView creditTV, privacy_policyTV, supportTV;

    InterstitialAd interstitialAd;
    AdRequest adRequest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,"ca-app-pub-3531666375863646/3439183458");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3531666375863646/2283590861");
        adRequest2 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest2);

        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                super.onAdClosed();

                finish();
                MainActivity.this.finish();

                interstitialAd.loadAd(adRequest2);
            }
        });

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WhaleActivity.class);
                startActivity(intent);
            }
        });

        creditTV = findViewById(R.id.creditTV);
        creditTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=vYDCgNYEkKI");
                Intent rate_intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(rate_intent);
            }
        });

        privacy_policyTV = findViewById(R.id.privacy_policyTV);
        privacy_policyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://whalesoundstosleep.blogspot.com/p/privacy-policy.html");
                Intent rate_intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(rate_intent);
            }
        });

        supportTV = findViewById(R.id.supportTV);
        supportTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Support");
                builder.setMessage("If you want to support me, just click the ads or review on Google Play :)");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }
}
