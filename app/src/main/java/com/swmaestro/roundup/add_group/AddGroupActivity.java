package com.swmaestro.roundup.add_group;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;
import android.widget.Toast;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.dto.RequestInfo;
import com.swmaestro.roundup.server_connector.RequestConfigurations;
import com.swmaestro.roundup.server_connector.ServerConnector;
import java.util.concurrent.ExecutionException;

/**
 * Created by JeongMinCha on 16. 3. 14..
 */
public class AddGroupActivity extends AppCompatActivity implements Button.OnClickListener {

    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    private EditText editTextGroupName;
    private EditText editTextPlace;
    private EditText editTextBelongTo;
    private EditText editTextFoundationDate;
    private EditText editTextCurrentTurn;

    private ToggleButton toggleButtonEnrolling;

    private ImageButton imageButtonLogo;
    private ImageButton imageButtonCover;

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

        imageButtonLogo = (ImageButton) findViewById(R.id.ib_add_logo_file);
        imageButtonCover = (ImageButton) findViewById(R.id.ib_add_cover_image);
        imageButtonLogo.setOnClickListener(this);
        imageButtonCover.setOnClickListener(this);

        buttonAddGroup = (Button) findViewById(R.id.btn_add_group);
        buttonAddGroup.setOnClickListener(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_group:
                this.sendGroupInfoToServer();
                finish();
                break;

            case R.id.ib_add_logo_file: {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
                break;

            case R.id.ib_add_cover_image: {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
                break;

            default:
                break;
        }
    }

    private void sendGroupInfoToServer() {
        // TODO: make the method to send new group information to the server
        RequestConfigurations rcfg = new RequestConfigurations();
        RequestInfo info = rcfg.getAddGroupRequestInfo(editTextGroupName.getText().toString(),
                editTextPlace.getText().toString(),
                editTextBelongTo.getText().toString(),
                editTextFoundationDate.getText().toString(),
                toggleButtonEnrolling.isChecked());
        AsyncTask<String, String, String> connector = new ServerConnector(ServerConnector.POST, info).execute("");
        try{
            String result = connector.get();
            Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
            toast.show();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //String groupPlace, String groupBelong, String groupFoundation, boolean groupEnroll
//        editTextGroupName = (EditText) findViewById(R.id.et_group_name);
//        editTextPlace = (EditText) findViewById(R.id.et_place);
//        editTextBelongTo = (EditText) findViewById(R.id.et_belong_to);
//        editTextFoundationDate = (EditText) findViewById(R.id.et_foundation_date);
//        editTextCurrentTurn = (EditText) findViewById(R.id.et_current_turn);
//
//        toggleButtonEnrolling = (ToggleButton) findViewById(R.id.tb_enrolling);
//
//        imageButtonLogo = (ImageButton) findViewById(R.id.ib_add_logo_file);
//        imageButtonCover = (ImageButton) findViewById(R.id.ib_add_cover_image);
//        imageButtonLogo.setOnClickListener(this);
//        imageButtonCover.setOnClickListener(this);
//
//        buttonAddGroup = (Button) findViewById(R.id.btn_add_group);
//        buttonAddGroup.setOnClickListener(this);
    }
}
