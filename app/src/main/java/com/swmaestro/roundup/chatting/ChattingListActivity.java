package com.swmaestro.roundup.chatting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class ChattingListActivity extends NavigationDrawerActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_list);
        super.makeNavigationDrawer();
    }
}
