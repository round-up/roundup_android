package com.swmaestro.roundup.club;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;
import com.swmaestro.roundup.server_connector.ServerConfig;
import com.swmaestro.roundup.utils.ImageHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lk on 16. 3. 8..
 */
public class ClubActivity extends NavigationDrawerActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerViewAdapter adapter;
    private CollapsingToolbarLayout collapsingToolbar;
    private int mutedColor = R.attr.colorPrimary;
    private Group group;
    private ImageView iv_club_logo;
    RecyclerView recyclerView;
    private ImageView iv_club_cover;
    int groupPk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_home);

        Intent intent = getIntent();
        groupPk = intent.getIntExtra("group_id", 1);

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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        iv_club_cover = (ImageView) findViewById(R.id.iv_club_cover);

        group = Group.setGroup(0,"","","","","","",true,"","",0, "");

        adapter = new RecyclerViewAdapter(getApplicationContext(), group);
        recyclerView.setAdapter(adapter);
        iv_club_logo = (ImageView) findViewById(R.id.iv_club_logo);
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

//    private void loadData(){
//        Feed feed = new Feed("제목", "작성자", R.mipmap.ic_launcher, "내용내용내용내용");
//        List<Feed> feedList = new ArrayList<>();
//
//        for (int i = 0 ; i < 20 ; i++) {
//            feed = new Feed("제목 " + i, "작성자", R.mipmap.ic_launcher, "내용내용내용내용");
//            feedList.add(feed);
//        }
//
//        adapter.setFeedList(feedList);
//    }


    private void loadData() {
        LoadGroupInfo();
        LoadFeed();
    }

    private void LoadGroupInfo() {
        HashMap<String, String> request = new HashMap<>();
        request.put("model", Request.Method.GET + "");
        request.put("url", ServerConfig.BASE_URL + "group/" + groupPk);

        Log.i("url", request.get("url"));

        JsonObjectRequest groupRequest = new JsonObjectRequest(request, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    group = Group.setGroup(response.getInt("id"), response.getString("group_belong"), response.getString("group_category"), response.getString("group_name"),
                            response.getString("group_description"), response.getString("group_start_date"), response.getString("group_place"), response.getBoolean("group_recruit_state"),
                            response.getString("group_leader_email"), response.getString("group_logo"), response.getInt("group_gisoo"), response.getString("group_cover"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject o = response.getJSONObject("united_group");
                    JSONArray a = o.getJSONArray("group_list");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject oo = a.getJSONObject(i);
                        group.setOther_logo(oo.getString("group_logo"));
                        group.setOther_name(oo.getString("group_name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray a = response.getJSONArray("users");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject o = a.getJSONObject(i);
                        User user = new User(o.getString("user_profile_image"), o.getString("user_phone_number"), o.getString("user_birth"), o.getBoolean("user_gender"), o.getString("user_name"), o.getString("email"));
                        group.addUser(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                iv_club_logo.setImageBitmap(group.getGroup_logo());
                collapsingToolbar.setTitle(group.getGroup_name());
                iv_club_cover.setImageBitmap(group.getGroup_cover());


                adapter = new RecyclerViewAdapter(getApplicationContext(), group);
                recyclerView.setAdapter(adapter);

            }
            //mAdapter.notifyDataSetChanged();
            //hideprogㅈrassDialog();                                                      // Hide PrograssDialog at the end of the recipe loaded
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", error.toString());
                //hideprograssDialog();
            }
        });
        Volley.newRequestQueue(this).add(groupRequest);
    }

    private void LoadFeed() {
        HashMap<String, String> request = new HashMap<>();
        request.put("model", Request.Method.GET + "");
        int count = 10;
        request.put("url", ServerConfig.BASE_URL + "group_feed/group/" + groupPk + "/" + count);
        Volley.newRequestQueue(this).add(JsonArrayRequest.createJsonRequestToken(request, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Feed.list = new ArrayList<Feed>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o = response.getJSONObject(i);
                        ArrayList<Bitmap> imageList = new ArrayList<Bitmap>();
                        for(int j=0; j<o.getJSONArray("image_list").length(); j++){
                            JSONObject o2 = o.getJSONArray("image_list").getJSONObject(j);
                            imageList.add(ImageHandler.getInstance().decodeBase64ToImage(o2.getString("feed_image")));
                        }
                        ArrayList<Comment> commentList = new ArrayList<Comment>();
                        for(int j=0; j<o.getJSONArray("comment_list").length(); j++){
                            JSONObject o2 = o.getJSONArray("comment_list").getJSONObject(j);
                            commentList.add(new Comment(o2.getString("email"), o2.getString("comment_date"), o2.getString("comment_content"), o2.getString("comment_title")));
                        }
                        Feed feed = new Feed(o.getInt("feed_access_modifier"), o.getString("feed_tags"), o.getJSONArray("like_list").length(), imageList, o.getString("feed_title"), o.getString("feed_date"), o.getString("email"),
                                o.getString("feed_type"), o.getString("feed_content"), commentList);
                        Feed.list.add(feed);
                        System.out.println(feed.getEmail());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.setFeedList(Feed.list);
                for(int i=0; i<Feed.list.size(); i++){
                    System.out.println(Feed.list.get(i).getEmail());
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", error.toString());
            }
        }));
    }

}
