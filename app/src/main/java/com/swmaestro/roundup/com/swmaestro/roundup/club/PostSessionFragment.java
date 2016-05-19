package com.swmaestro.roundup.com.swmaestro.roundup.club;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swmaestro.roundup.R;

/**
 * Created by lk on 16. 5. 19..
 */
public class PostSessionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_club_post_session, container, false);
    }

}
