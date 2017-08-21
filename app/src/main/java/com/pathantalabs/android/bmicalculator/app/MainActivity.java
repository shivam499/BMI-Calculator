package com.pathantalabs.android.bmicalculator.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.pathantalabs.android.bmicalculator.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String APP_SHARE_LINK = "https://play.google.com/store/apps/details?id=com.pathantalabs.android.bmicalculator";

    private RecyclerView recyclerView;
    private InterstitialAd interstitialAd;
    private final List<Object> recyclerItemsView = new ArrayList<>();
    public static final int ITEMS_PER_AD = 4;
    private boolean isActivityOpen = true;

    private final int[] HeadTextName = new int[]{R.string.bmi_calculator,
            R.string.bmr_calculator, R.string.calorie_calculator,
            R.string.bsa_calculator, R.string.ibw_calculator,
            R.string.lbm_calculator};

    private final int[] DescriptionTextName = new int[]{R.string.bmi_full,
            R.string.bmr_full, R.string.cal_full,
            R.string.bsa_full, R.string.ibw_full, R.string.lbm_full};

    private final int[] ImagesCalculator = new int[]{
            R.drawable.bmi, R.drawable.bmr, R.drawable.calorie, R.drawable.bsa,
            R.drawable.ibw, R.drawable.lbm
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReplaceFont.ReplaceDefaultFont(this);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,getResources().getString(R.string.appId));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection deprecation
        toolbar.setTitleTextColor(getResources().getColor(R.color.title_text_color));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        addDataToList();
        addNativeAds();

        RecyclerView.Adapter recycleAdapter = new RecycleAdapter(this, recyclerItemsView);

        recyclerView.setAdapter(recycleAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInterstitialAd();
    }

    private void loadInterstitialAd(){
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interStUnit));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.show();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                final Handler adHandler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (isActivityOpen){
                            interstitialAd.show();
                            adHandler.postDelayed(this,25000);
                        }
                    }
                };
                adHandler.postDelayed(runnable,25000);
            }
        });
    }

    private void addNativeAds() {
        for (int i = 1; i <= recyclerItemsView.size(); i += 4) {
            final NativeExpressAdView nativeAds = new NativeExpressAdView(MainActivity.this);
            nativeAds.setAdUnitId(getResources().getString(R.string.nativeUnit));
            recyclerItemsView.add(i, nativeAds);
        }
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                final float density = MainActivity.this.getResources().getDisplayMetrics().density;
                AdSize adSize = new AdSize(
                        (int) (recyclerView.getWidth() / density), 100
                );
                for (int i = 1; i <= recyclerItemsView.size(); i += 4) {
                    NativeExpressAdView adViewToSize = (NativeExpressAdView) recyclerItemsView.get(i);
                    adViewToSize.setAdSize(adSize);
                    adViewToSize.loadAd(new AdRequest.Builder().build());
                }
            }
        });
    }


    private void addDataToList() {
        for (int i = 0; i < HeadTextName.length; i++) {
            DataItem dataItem = new DataItem(HeadTextName[i], DescriptionTextName[i], ImagesCalculator[i]);
            recyclerItemsView.add(dataItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bmi_calculate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about:
                about();
                break;
            case R.id.rateUs:
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_SHARE_LINK));
                startActivity(rateIntent);
                break;
            case R.id.disclaimer:
                Disclaimer();
                break;
            case R.id.shareApp:
                shareApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareApp() {
        Intent chooserShare;
        String shareAppString = "Bmi Calculator gives a chance to stay Healthy & stay Fit.\nFor More : " + APP_SHARE_LINK;

        Intent shareAppIntent = new Intent(Intent.ACTION_SEND);
        shareAppIntent.putExtra(Intent.EXTRA_TEXT, shareAppString.trim());
        shareAppIntent.setType("text/plain");
        chooserShare = Intent.createChooser(shareAppIntent, "Share the App");
        startActivity(chooserShare);
    }
    private void about() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("BMI Calculator");
        alert.setMessage("Start your fitness journey with BMI Android Application.\nOur Moto is everyone has rights to stay Healthy & Fit.");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
    private void Disclaimer() {

        AlertDialog.Builder disClaimer = new AlertDialog.Builder(this);
        disClaimer.setTitle("Disclaimer");
        disClaimer.setMessage(getResources().getString(R.string.disClaimer_string));
        disClaimer.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        disClaimer.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityOpen = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }
}

