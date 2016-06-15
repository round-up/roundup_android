package com.swmaestro.roundup.add_group;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.swmaestro.roundup.R;
import com.swmaestro.roundup.dto.Group;
import com.swmaestro.roundup.dto.RequestInfo;
import com.swmaestro.roundup.server_connector.RequestConfigurations;
import com.swmaestro.roundup.server_connector.ServerConfig;
import com.swmaestro.roundup.server_connector.ServerConnector;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by JeongMinCha on 16. 3. 14..
 */
public class AddGroupActivity extends AppCompatActivity implements Button.OnClickListener {

    private static final int BACKGROUND_IMG = 1;
    private static final int PROFILE_IMG = 2;
    private int currentImageToUpload = 0;

    // this is the action code we use in our intent,
    // this way we know we're looking at the response from our own action
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    private ImageButton imgButtonBackground;
    private ImageButton imgButtonProfile;
    private EditText editTextGroupName;
    private EditText editTextPlace;
    private EditText editTextBelonging;
    private EditText editTextFoundationDay;
    private EditText editTextGroupCounter;
    private ToggleButton toggleButtonRecruiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        imgButtonBackground = (ImageButton) findViewById(R.id.ib_background);
        imgButtonProfile = (ImageButton) findViewById(R.id.ib_profile_img);
        editTextGroupName = (EditText) findViewById(R.id.et_group_name);
        editTextPlace = (EditText) findViewById(R.id.et_group_place);
        editTextBelonging = (EditText) findViewById(R.id.et_group_belonging);
        editTextFoundationDay = (EditText) findViewById(R.id.et_group_foundation_day);
        editTextGroupCounter = (EditText) findViewById(R.id.et_group_counter);
        toggleButtonRecruiting = (ToggleButton) findViewById(R.id.tb_recruiting);

        imgButtonBackground.setColorFilter(0x88000000); // Dim image button for background image
        imgButtonBackground.setOnClickListener(this);

        imgButtonProfile.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add_group_complete:
                sendGroupInfoToServer();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {
            case R.id.ib_background:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                currentImageToUpload = BACKGROUND_IMG;
                startActivityForResult(Intent.createChooser(intent, "Select Background Image"), SELECT_PICTURE);
                break;
            case R.id.ib_profile_img:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                currentImageToUpload = PROFILE_IMG;
                startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), SELECT_PICTURE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);

            Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
            switch(currentImageToUpload) {
                case BACKGROUND_IMG:
                    imgButtonBackground.setImageBitmap(bitmap);
                    break;
                case PROFILE_IMG:
                    imgButtonProfile.setImageBitmap(bitmap);
                    break;
                default:
                    break;
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


    private void sendGroupInfoToServer() {
        // TODO: make the method to send new group information to the server
        String url = ServerConfig.BASE_URL + "group/";
        Group group = new Group("", "", "", "RoundUp", "Seoul", "Soma", "2016.06.24", true);
        JSONObject object = group.getJsonObject();

        JsonObjectRequest request
                = new JsonObjectRequest(url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("all", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(this).add(request);
    }
}
