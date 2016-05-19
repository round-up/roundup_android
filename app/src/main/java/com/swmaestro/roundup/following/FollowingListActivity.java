package com.swmaestro.roundup.following;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class FollowingListActivity extends NavigationDrawerActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_list);
        super.makeNavigationDrawer();
    }
}
