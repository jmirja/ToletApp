package com.rit.basa_bari.post_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rit.basa_bari.R;

public class AdsHostelPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ads_hostel_post );
        setTitle( "Post Ads For Hostel" );
    }
}
