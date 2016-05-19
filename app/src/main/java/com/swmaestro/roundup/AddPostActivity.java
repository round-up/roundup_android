package com.swmaestro.roundup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.com.swmaestro.roundup.club.PostEventFragment;
import com.swmaestro.roundup.com.swmaestro.roundup.club.PostNoticeFragment;
import com.swmaestro.roundup.com.swmaestro.roundup.club.PostSayFragment;
import com.swmaestro.roundup.com.swmaestro.roundup.club.PostSessionFragment;

/**
 * Created by lk on 16. 5. 15..
 */
public class AddPostActivity extends AppCompatActivity{

    private Button btn_session;
    private Button btn_notice;
    private Button btn_event;
    private Button btn_say;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private Fragment fr;


    @Override
    public void onCreate(Bundle savedInstanced){
        super.onCreate(savedInstanced);
        setContentView(R.layout.activity_add_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init(){
        btn_session = (Button) findViewById(R.id.btn_add_club_post_session);
        btn_notice = (Button) findViewById(R.id.btn_add_club_post_notice);
        btn_event = (Button) findViewById(R.id.btn_add_club_post_event);
        btn_say = (Button) findViewById(R.id.btn_add_club_post_say);
        fm = getFragmentManager();

        fr = new PostSessionFragment();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fl_add_club_post, fr);
        fragmentTransaction.commit();


        btn_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fr = new PostSessionFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr);
                fragmentTransaction.commit();
            }
        });

        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fr = new PostNoticeFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr);
                fragmentTransaction.commit();
            }
        });

        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fr = new PostEventFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr);
                fragmentTransaction.commit();
            }
        });

        btn_say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fr = new PostSayFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr);
                fragmentTransaction.commit();
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_menu_post:
                //todo Post
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_post_actionbar, menu);
        return true;
    }
}
