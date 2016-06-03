package com.swmaestro.roundup.navigation;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    private final int idHomeFeed = 0;
    private final int idMyGroups = 1;
    private final int idFollowingGroups = 2;
    private final int idAddGroup = 3;
    private final int idSettings = 4;

    private final String HOME_FEED = "Home Feed";
    private final String MY_GROUPS = "My Groups";
    private final String FOLLOWING_GROUPS = "Following Groups";
    private final String ADD_GROUP = "Add a New Group";
    private final String SETTINGS = "Settings";

    private Menu mNavigationMenu;

    private List<String> followingGroupTitles;
    private List<Integer> followingGroupIcons;

    private NavigationHeader mHeader;

    RealmConfiguration realmConfig;
    Realm realm;

    protected void makeNavigationDrawer() {
        // Processing Realm DataBase.
        prepareRealm();
        createFollowingTable();
        accessFollowingTable();
        createNavigationHeaderTable();
        accessNavigationHeaderTable();

        // Make components by information derived from Realm DB.
        makeDrawer();
        makeNavigationView();
        makeNavigationHeader();
    }

    private void makeNavigationHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_home_feed);
        TextView tvName = (TextView) headerView.findViewById(R.id.nav_head_name);
        TextView tvEmail = (TextView) headerView.findViewById(R.id.nav_head_email);
        ImageView ivIcon = (ImageView) headerView.findViewById(R.id.nav_head_image);

        if (mHeader != null) {
            tvName.setText(mHeader.getName());
            tvEmail.setText(mHeader.getEmailAddr());
            ivIcon.setImageResource(mHeader.getResIconImage());
            headerView.setBackgroundResource(mHeader.getResBackImage());
        }
    }

    private void prepareRealm() {
        realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);
    }

    private void createFollowingTable() {
        // TODO: Delete this method after making a routine to get data from server and save it to RealmDB.
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

    private void createNavigationHeaderTable() {
        // TODO: Delete this method after making a routine to get data from server and save it to RealmDB.
        final NavigationHeader header = new NavigationHeader();
        header.setName("JeongMinCha");
        header.setEmailAddr("cjm9236@naver.com");
        header.setResBackImage(R.drawable.nav_header_background_example);
        header.setResIconImage(R.drawable.ic_action_dock);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(header);
            }
        });
    }

    private void accessNavigationHeaderTable() {
        mHeader = realm.where(NavigationHeader.class).findFirst();
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
        mNavigationMenu.add(R.id.nav_group1, idHomeFeed, Menu.NONE, HOME_FEED)
                .setIcon(R.drawable.ic_action_dock);
        SubMenu subMenu = mNavigationMenu
                .addSubMenu(R.id.nav_group1, idMyGroups, Menu.NONE, MY_GROUPS);
        for (int i = 0; i < followingGroupIcons.size(); i++) {
            subMenu.add(followingGroupTitles.get(i)).setIcon(followingGroupIcons.get(i));
        }

        mNavigationMenu.add(R.id.nav_group2, idFollowingGroups, Menu.NONE, FOLLOWING_GROUPS)
                .setIcon(R.drawable.ic_action_forward);
        mNavigationMenu.add(R.id.nav_group2, idAddGroup, Menu.NONE, ADD_GROUP)
                .setIcon(R.drawable.ic_action_add_group);
        mNavigationMenu.add(R.id.nav_group2, idSettings, Menu.NONE, SETTINGS)
                .setIcon(R.drawable.ic_action_settings);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = item.getTitle().toString();

        // close the navigation drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // Process the intent.
        Intent intent;
        switch (title) {
            case HOME_FEED:
                intent = new Intent(this, HomeFeedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case FOLLOWING_GROUPS:
                intent = new Intent(this, FollowingListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case ADD_GROUP:
                intent = new Intent(this, AddGroupActivity.class);
                startActivity(intent);
                break;
            case SETTINGS:
                intent = new Intent(this, SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
