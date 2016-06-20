package com.swmaestro.roundup.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.swmaestro.roundup.R;
import com.swmaestro.roundup.club.ClubActivity;
import com.swmaestro.roundup.login.LoginActivity;
import com.swmaestro.roundup.login.SaveSharedPreference;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;
import com.swmaestro.roundup.server_connector.ServerConfig;
import com.swmaestro.roundup.utils.HomeFeedData;

import org.json.JSONObject;


public class HomeFeedActivity extends NavigationDrawerActivity {

    private JSONObject data;

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

        mClubSummaryFeedListAdapter = new ClubSummaryFeedListAdapter(this);
        mInterestingActivitiesAdapter = new HomeFeedListAdapter(this);
        this.loadHomeFeedData(getIntent().getStringExtra("user_name"));
        this.loadGroupData();
    }

    private void loadGroupData() {
        // TODO: Volley GET method to retrieve group info
    }

    private void loadHomeFeedData(String userEmail) {
        String url = ServerConfig.BASE_URL + "home_feed/" + userEmail;

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                data = response;
                mClubSummaryFeedListAdapter.setData(data);
                mInterestingActivitiesAdapter.setData(data);

                // TODO: move this two statements to response listener in method 'loadGroupData' after the method is implemented
                makeClubSummarySection();
                makeInterestingActivitiesSection();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(this).add(request);
    }

    private void makeClubSummarySection() {
        // RecyclerView and StaggeredGridLayoutManager for Club Summary Feed Section.
        mClubSummaryRecyclerView = (RecyclerView) findViewById(R.id.club_summary_feed_list);
        mClubSummaryLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL);
        mClubSummaryRecyclerView.setLayoutManager(mClubSummaryLayoutManager);
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
        mInterestingActivitiesRecyclerView.setAdapter(mInterestingActivitiesAdapter);

        HomeFeedListAdapter.OnItemClickListener onItemClickListener
                = new HomeFeedListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), ClubActivity.class);
                intent.putExtra("name", "RoundUp");
                startActivity(intent);
            }
        };
        mInterestingActivitiesAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            SaveSharedPreference.clearUserName(HomeFeedActivity.this);
            Intent intent = new Intent(HomeFeedActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
