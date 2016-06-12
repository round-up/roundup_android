package com.swmaestro.roundup.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.club.ClubActivity;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;


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
//                Intent intent = new Intent(getApplicationContext(), ClubActivity.class);
//                intent.putExtra("name", HomeFeedData.homeFeedList().get(position).name);
//                startActivity(intent);
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
        getMenuInflater().inflate(R.menu.home_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
