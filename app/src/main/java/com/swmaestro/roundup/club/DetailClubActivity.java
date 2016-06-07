package com.swmaestro.roundup.club;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swmaestro.roundup.R;

/**
 * Created by lk on 16. 6. 2..
 */
public class DetailClubActivity extends AppCompatActivity {

    public void onCreate(Bundle saveInstanced) {
        super.onCreate(saveInstanced);
        setContentView(R.layout.activity_detail_club);

        LinearLayout linearLayoutMain = (LinearLayout) findViewById(R.id.ll_main);

        int n = 5;
        // todo 시작
        for (int i = n; i > 0; i--) {
            CardView cardView = new CardView(getApplicationContext());
            LinearLayout.LayoutParams p =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(0,20,0,20);
            cardView.setLayoutParams(p);

            LinearLayout layoutRoot = new LinearLayout(getApplicationContext());
            layoutRoot.setOrientation(LinearLayout.VERTICAL);

            final HorizontalScrollView scrollView2 = new HorizontalScrollView(getApplicationContext());
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(R.drawable.ic_member);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(30, 30, 30, 30);
            imageView.setLayoutParams(layoutParams);

            TextView textView = new TextView(getApplicationContext());
            textView.setText(i + "기 멤버 리스트");
            textView.setTextColor(Color.parseColor("#000000"));
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params3.setMargins(0, 30, 30, 30);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setLayoutParams(params3);

            ImageButton imageView2 = new ImageButton(getApplicationContext());
            imageView2.setImageResource(R.drawable.ic_member);
            imageView2.setScaleType(ImageView.ScaleType.CENTER);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(100, 100);
            layoutParams2.setMargins(30, 30, 30, 30);
            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (scrollView2.getVisibility() == View.GONE)
                        scrollView2.setVisibility(View.VISIBLE);
                    else
                    scrollView2.setVisibility(View.GONE);

                }
            });
            imageView2.setLayoutParams(layoutParams2);


            LinearLayout linearLayout2 = new LinearLayout(getApplicationContext());
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout2.setGravity(Gravity.END);
            linearLayout2.addView(imageView2);


            HorizontalScrollView.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
            scrollView2.setLayoutParams(params2);
            LinearLayout ll4 = new LinearLayout(getApplicationContext());
            ll4.setLayoutParams(params2);
            ll4.setBackgroundColor(Color.parseColor("#ffffff"));
            scrollView2.addView(ll4);
            for (int k = 0; k < 10; k++) {
                Drawable temp1 = getDrawable(R.mipmap.ic_launcher);
                ImageView iv = new ImageView(getApplicationContext());
                iv.setBackground(temp1);
                ll4.addView(iv);
            }

            LinearLayout layout = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
            layout.setLayoutParams(params1);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(imageView);
            layout.addView(textView);
            layout.addView(linearLayout2);
            layout.setBackgroundColor(Color.parseColor("#ffffff"));

            layoutRoot.addView(layout);
            layoutRoot.addView(scrollView2);
            cardView.addView(layoutRoot);

            linearLayoutMain.addView(cardView);
        }

    }
}
