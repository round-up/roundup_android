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
import com.swmaestro.roundup.following.FollowingListActivity;
import com.swmaestro.roundup.home.HomeFeedActivity;
import com.swmaestro.roundup.setting.SettingActivity;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final private String[] menuTitles = {"Home Feed",
            "Followings",
            "Chatting",
            "Setting",
            "Add a New Group"};
    final private int[] menuIcons = {R.drawable.ic_action_dock,
            R.drawable.ic_action_forward,
            R.drawable.ic_action_chat,
            R.drawable.ic_action_settings,
            R.drawable.ic_action_add_group};

    private Menu mNavigationMenu;

    protected void makeNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mNavigationMenu = navigationView.getMenu();
        mNavigationMenu.clear();
        for (int idx = 0; idx < menuTitles.length; idx++) {
            if (idx == 1) {
                SubMenu subMenu = mNavigationMenu.addSubMenu(menuTitles[idx]);
                subMenu.add("Sub Menu 1");
                subMenu.add("Sub Menu 2");
            } else {
                mNavigationMenu.add(menuTitles[idx]).setIcon(menuIcons[idx]);
            }
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
            case R.id.nav_following:
                intent = new Intent(this, FollowingListActivity.class);
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
