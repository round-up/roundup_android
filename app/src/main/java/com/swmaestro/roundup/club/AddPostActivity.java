package com.swmaestro.roundup.club;

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
    private Fragment[] fr;


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

        fr = new Fragment[4];
        fr[0] = new PostSessionFragment();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fl_add_club_post, fr[0]);
        fragmentTransaction.commit();


        btn_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fr[0] == null)
                    fr[0] = new PostSessionFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr[0]);
                fragmentTransaction.commit();
            }
        });

        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fr[1] == null)
                    fr[1] = new PostNoticeFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr[1]);
                fragmentTransaction.commit();
            }
        });

        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fr[2] == null)
                    fr[2] = new PostEventFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr[2]);
                fragmentTransaction.commit();
            }
        });

        btn_say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fr[3] == null)
                    fr[3] = new PostSayFragment();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fl_add_club_post, fr[3]);
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
