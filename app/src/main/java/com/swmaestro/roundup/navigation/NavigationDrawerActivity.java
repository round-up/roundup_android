package com.swmaestro.roundup.navigation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.swmaestro.roundup.dto.RequestInfo;
import com.swmaestro.roundup.following.FollowingListActivity;
import com.swmaestro.roundup.home.HomeFeedActivity;
import com.swmaestro.roundup.server_connector.RequestConfigurations;
import com.swmaestro.roundup.server_connector.ServerConnector;
import com.swmaestro.roundup.setting.SettingActivity;
import com.swmaestro.roundup.utils.ImageHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;

    RealmConfiguration realmConfig;
    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareRealm();
        createFollowingTable();
        accessFollowingTable();
        createNavigationHeaderTable();
        accessNavigationHeaderTable();
    }

    protected void makeNavigationDrawer() {
        makeToolbar();
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
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.p00_img_profile_default);
            ivIcon.setImageBitmap(ImageHandler.getInstance().getRoundedShape(icon));
        }
    }

    private void prepareRealm() {
        realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);
    }

    private void createFollowingTable() {
        // TODO: Delete this method after making a routine to get data from server and save it to RealmDB.
//        final List<MyGroupMenuItem> groups = getGroupInformationFromServer("choiilji@gmail.com");
        final List<MyGroupMenuItem> groups = new ArrayList<>();
        groups.add(new MyGroupMenuItem(1, "SubGroup 1", R.drawable.ic_action_dock));
        groups.add(new MyGroupMenuItem(2, "SubGroup 2", R.drawable.ic_action_dock));
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

        RealmResults<MyGroupMenuItem> realmResults
                = realm.where(MyGroupMenuItem.class).findAll();
        for (int idx = 0; idx < realmResults.size(); idx ++) {
            followingGroupTitles.add(realmResults.get(idx).getTitle());
            followingGroupIcons.add(realmResults.get(idx).getIconRes());
        }
    }

    private void createNavigationHeaderTable() {
        // TODO: Delete this method after making a routine to get data from server and save it to RealmDB.
        final NavigationHeader header = new NavigationHeader();
        header.setName("IlJi Choi");
        header.setEmailAddr("choiilji@gmail.com");
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

    private void makeToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void makeDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void makeNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        Drawable homeIcon = getResources().getDrawable(R.drawable.p00_ic_home);
        Drawable sendIcon = getResources().getDrawable(R.drawable.p00_ic_send);
        Drawable newIcon = getResources().getDrawable(R.drawable.p00_ic_new);
        Drawable settingIcon = getResources().getDrawable(R.drawable.p00_ic_settings);

        mNavigationMenu = navigationView.getMenu();
        mNavigationMenu.add(R.id.nav_group1, idHomeFeed, Menu.NONE, HOME_FEED)
                .setIcon(homeIcon);
        SubMenu subMenu = mNavigationMenu
                .addSubMenu(R.id.nav_group1, idMyGroups, Menu.NONE, MY_GROUPS);
        for (int i = 0; i < followingGroupIcons.size(); i++) {
            subMenu.add("\t\t\t" + followingGroupTitles.get(i));
        }

        mNavigationMenu.add(R.id.nav_group2, idFollowingGroups, Menu.NONE, FOLLOWING_GROUPS)
                .setIcon(sendIcon);
        mNavigationMenu.add(R.id.nav_group2, idAddGroup, Menu.NONE, ADD_GROUP)
                .setIcon(newIcon);
        mNavigationMenu.add(R.id.nav_group2, idSettings, Menu.NONE, SETTINGS)
                .setIcon(settingIcon);
    }



    private ArrayList<MyGroupMenuItem> getGroupInformationFromServer(String email){
        ArrayList<MyGroupMenuItem> menuItems = new ArrayList<MyGroupMenuItem>();
        RequestConfigurations rcfg = new RequestConfigurations();
        RequestInfo info = rcfg.getGroupList(email);
        AsyncTask<String, String, String> connector = new ServerConnector(ServerConnector.POST_ONLY, info).execute("");
        try{
            String result = connector.get();
            JSONObject obj = new JSONObject(result);

            JSONArray leader_obj = new JSONArray((String)obj.get("leader"));
            JSONArray member_obj = new JSONArray((String)obj.get("member"));

            for (int i = 0 ; i < leader_obj.length() ;i++){
                JSONObject item = leader_obj.getJSONObject(i);
                int id = (Integer)item.get("pk");
                String title = (String)item.get("group_name");
                String logoData = (String)item.get("group_logo");
                menuItems.add(new MyGroupMenuItem((int)id, "*"+title, ImageHandler.getInstance().getImageResByBASE64Data(logoData, R.drawable.ic_action_dock)));
            }

            for (int i = 0 ; i < member_obj.length() ;i++){
                JSONObject item = member_obj.getJSONObject(i);
                int id = (Integer)item.get("pk");
                String title = (String)item.get("group_name");
                String logoData = (String)item.get("group_logo");
                menuItems.add(new MyGroupMenuItem((int)id, title, ImageHandler.getInstance().getImageResByBASE64Data(logoData, R.drawable.ic_action_dock)));
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException je){
            Log.e("JSON Error", je.getMessage());
            je.printStackTrace();
        }
        return menuItems;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = item.getTitle().toString();

        // close the navigation drawer.
        mDrawer.closeDrawer(GravityCompat.START);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
