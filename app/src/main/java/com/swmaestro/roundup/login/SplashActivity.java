package com.swmaestro.roundup.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.home.HomeFeedActivity;

/**
 * Created by JeongMinCha on 16. 6. 16..
 */
public class SplashActivity extends Activity {
    int SPLASH_TIME=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0,android.R.anim.fade_in);
                if (SaveSharedPreference.getUserName(SplashActivity.this).length() == 0) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, HomeFeedActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },SPLASH_TIME);
    }
}
