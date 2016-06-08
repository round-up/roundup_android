package com.swmaestro.roundup.setting;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.navigation.NavigationDrawerActivity;

/**
 * Created by JeongMinCha on 16. 5. 19..
 */
public class SettingActivity extends NavigationDrawerActivity {

    private Button btnSessionNoti;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        super.makeNavigationDrawer();

        btnSessionNoti = (Button) findViewById(R.id.btn_session_notify);
        btnSessionNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(SettingActivity.this, btnSessionNoti);
                popup.getMenuInflater().inflate(R.menu.session_noficiation, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        popupMenuItemClick(item);
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    private void popupMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.noti_one_hour:
                break;
            case R.id.noti_two_hour:
                break;
            default:
                break;
        }
    }
}
