package com.swmaestro.roundup.following;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class FollowingListActivity extends NavigationDrawerActivity {

    private Realm realm;
    private List<FollowingGroup> followingGroups;

    public FollowingListActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_list);
        super.makeNavigationDrawer();
    }

    @Override
    public void onStart() {
        super.onStart();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }

    private void createFollowingTable() {

    }
}
