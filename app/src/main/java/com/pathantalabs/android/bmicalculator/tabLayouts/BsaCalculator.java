package com.pathantalabs.android.bmicalculator.tabLayouts;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.pathantalabs.android.bmicalculator.R;
import com.pathantalabs.android.bmicalculator.BmiCustomAdapter;
import com.pathantalabs.android.bmicalculator.bsaFragment.BsaCalculateFragment;
import com.pathantalabs.android.bmicalculator.bsaFragment.BsaInfoFragment;

import java.util.ArrayList;
import java.util.List;


public class BsaCalculator extends AppCompatActivity {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();

    private InterstitialAd interstitialAd;
    private boolean isActivityOpen = true;

    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_tab);
        setTitle(getResources().getString(R.string.bsa_calculator));
        AdView adView = (AdView) findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());

        initialize();

        prepareDataResources();

        BmiCustomAdapter adapter = new BmiCustomAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInterstitialAd();
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //noinspection deprecation
        toolbar.setTitleTextColor(getResources().getColor(R.color.title_text_color));

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //noinspection deprecation
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.title_text_color)));

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

    private void prepareDataResources() {
        addData(new BsaCalculateFragment(),getResources().getString(R.string.bsa_calculator));
        addData(new BsaInfoFragment(),"BSA Info");

    }

    private void addData(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bsa,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.more_bsa){
            Uri bmi = Uri.parse("https://google.com");
            Intent search = new Intent(Intent.ACTION_WEB_SEARCH,bmi);
            search.putExtra(SearchManager.QUERY,"wiki:Body Surface Area");
            startActivity(search);
        }
        return super.onOptionsItemSelected(item);
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

