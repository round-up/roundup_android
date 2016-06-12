package com.swmaestro.roundup.club;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lk on 16. 3. 8..
 */
public class ClubActivity extends NavigationDrawerActivity implements NavigationView.OnNavigationItemSelectedListener, AppBarLayout.OnOffsetChangedListener{

    private RecyclerViewAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbar;
    private int mutedColor = R.attr.colorPrimary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_home);

        super.makeNavigationDrawer();

        FloatingActionButton writeButton = (FloatingActionButton) findViewById(R.id.fab);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddPostActivity.class));
            }
        });

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //collapsingToolbar.setCollapsedTitleTextColor(R.color.colorWhite);
        ImageView header = (ImageView) findViewById(R.id.iv_club_cover);

        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#000000"));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

        loadData();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadData(){
        Feed feed = new Feed("제목", "작성자", R.mipmap.ic_launcher, "내용내용내용내용");
        List<Feed> feedList = new ArrayList<>();

        for (int i = 0 ; i < 20 ; i++) {
            feed = new Feed("제목 " + i, "작성자", R.mipmap.ic_launcher, "내용내용내용내용");
            feedList.add(feed);
        }

        adapter.setFeedList(feedList);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        Log.i("",maxScroll + "  / " + percentage);
    }

}
