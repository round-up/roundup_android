package com.swmaestro.roundup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * Created by JeongMinCha on 16. 3. 14..
 */
public class AddGroupActivity extends AppCompatActivity {

    private EditText editTextGroupName;
    private EditText editTextPlace;
    private EditText editTextBelongTo;
    private EditText editTextFoundationDate;
    private EditText editTextCurrentTurn;

    private ToggleButton toggleButtonEnrolling;

    private Button buttonAddGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        editTextGroupName = (EditText) findViewById(R.id.et_group_name);
        editTextPlace = (EditText) findViewById(R.id.et_place);
        editTextBelongTo = (EditText) findViewById(R.id.et_belong_to);
        editTextFoundationDate = (EditText) findViewById(R.id.et_foundation_date);
        editTextCurrentTurn = (EditText) findViewById(R.id.et_current_turn);

        toggleButtonEnrolling = (ToggleButton) findViewById(R.id.tb_enrolling);

        buttonAddGroup = (Button) findViewById(R.id.btn_add_group);
    }
}
