package com.swmaestro.roundup.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.swmaestro.roundup.chatting.ChattingListActivity;
import com.swmaestro.roundup.following.FollowingListActivity;
import com.swmaestro.roundup.add_group.AddGroupActivity;
import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;
import com.swmaestro.roundup.setting.SettingActivity;

public class HomeFeedActivity extends NavigationDrawerActivity {

    private RecyclerView mClubSummaryRecyclerView;
    private StaggeredGridLayoutManager mClubSummaryLayoutManager;
    private ClubSummaryFeedListAdapter mClubSummaryFeedListAdapter;

    private RecyclerView mInterestingActivitiesRecyclerView;
    private StaggeredGridLayoutManager mInterestingActivitiesLayoutManager;
    private HomeFeedListAdapter mInterestingActivitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed);

        super.makeNavigationDrawer();
        this.makeClubSummarySection();
        this.makeInterestingActivitiesSection();
    }

    private void makeClubSummarySection() {
        // RecyclerView and StaggeredGridLayoutManager for Club Summary Feed Section.
        mClubSummaryRecyclerView = (RecyclerView) findViewById(R.id.club_summary_feed_list);
        mClubSummaryLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL);
        mClubSummaryRecyclerView.setLayoutManager(mClubSummaryLayoutManager);

        mClubSummaryFeedListAdapter = new ClubSummaryFeedListAdapter(this);
        mClubSummaryRecyclerView.setAdapter(mClubSummaryFeedListAdapter);

        ClubSummaryFeedListAdapter.OnItemClickListener onItemClickListener
                = new ClubSummaryFeedListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

            }
        };
        mClubSummaryFeedListAdapter.setOnItemClickListener(onItemClickListener);
    }

    private void makeInterestingActivitiesSection() {
        // RecyclerView and StaggeredGridLayoutManager for Interesting Activities Section.
        mInterestingActivitiesRecyclerView = (RecyclerView) findViewById(R.id.interesting_club_activity_list);
        mInterestingActivitiesLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        mInterestingActivitiesRecyclerView.setLayoutManager(mInterestingActivitiesLayoutManager);

        mInterestingActivitiesAdapter = new HomeFeedListAdapter(this);
        mInterestingActivitiesRecyclerView.setAdapter(mInterestingActivitiesAdapter);

        HomeFeedListAdapter.OnItemClickListener onItemClickListener
                = new HomeFeedListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

            }
        };
        mInterestingActivitiesAdapter.setOnItemClickListener(onItemClickListener);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
