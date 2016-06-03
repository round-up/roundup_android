package com.swmaestro.roundup.following;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class FollowingListActivity extends NavigationDrawerActivity {

    private Realm realm;
    private List<FollowingGroup> followingGroups;

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

        followingGroups = new ArrayList<>();

        createFollowingTable();
        accessFollowingTable();
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }

    private void createFollowingTable() {
        // TODO: Delete this method after making a routine to get data from server and save it to RealmDB.
        final List<FollowingGroup> groups = new ArrayList<>();
        groups.add(new FollowingGroup(1, "레알", true, "IT 실전 창업 동아리", R.drawable.ic_action_dock));
        groups.add(new FollowingGroup(2, "넥스터즈", false, "IT 실전 창업 동아리 2", R.drawable.ic_action_dock));
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(groups);
            }
        });
    }

    private void accessFollowingTable() {
        RealmResults<FollowingGroup> results = realm.where(FollowingGroup.class).findAll();
        for(int idx = 0; idx < results.size(); idx++) {
            followingGroups.add(results.get(idx));
        }
    }
}
