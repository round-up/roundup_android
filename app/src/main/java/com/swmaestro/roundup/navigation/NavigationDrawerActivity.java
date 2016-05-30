package com.swmaestro.roundup.navigation;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.add_group.AddGroupActivity;
import com.swmaestro.roundup.chatting.ChattingListActivity;
import com.swmaestro.roundup.following.Following;
import com.swmaestro.roundup.following.FollowingListActivity;
import com.swmaestro.roundup.home.HomeFeedActivity;
import com.swmaestro.roundup.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu mNavigationMenu;
    private List<String> followingGroupTitles;
    private List<Integer> followingGroupIcons;

    RealmConfiguration realmConfig;
    Realm realm;

    protected void makeNavigationDrawer() {
        prepareRealm();
        createFollowingTable();
        accessFollowingTable();
        makeDrawer();
        makeNavigationView();
    }

    private void prepareRealm() {
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);
    }

    private void createFollowingTable() {
        final List<Following> groups = new ArrayList<>();
        groups.add(new Following(1, "SubGroup 1", R.drawable.ic_action_dock));
        groups.add(new Following(2, "SubGroup 2", R.drawable.ic_action_dock));
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(groups);
            }
        });
    }

    private void accessFollowingTable() {
        followingGroupTitles = new ArrayList<>();
        followingGroupIcons = new ArrayList<>();

        RealmResults<Following> realmResults
                = realm.where(Following.class).findAll();
        for (int idx = 0; idx < realmResults.size(); idx ++) {
            followingGroupTitles.add(realmResults.get(idx).getTitle());
            followingGroupIcons.add(realmResults.get(idx).getIconRes());
        }
    }

    private void makeDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void makeNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mNavigationMenu = navigationView.getMenu();
        SubMenu subMenu = mNavigationMenu.addSubMenu("Followings");
        for (int i = 0; i < followingGroupIcons.size(); i++) {
            subMenu.add(followingGroupTitles.get(i)).setIcon(followingGroupIcons.get(i));
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // close the navigation drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // Process the intent.
        Intent intent;
        switch (id) {
            case R.id.nav_home_feed:
                intent = new Intent(this, HomeFeedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.nav_chatting:
                intent = new Intent(this, ChattingListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.nav_setting:
                intent = new Intent(this, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.nav_add_group:
                intent = new Intent(this, AddGroupActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
