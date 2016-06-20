package com.swmaestro.roundup.login;

/**
 * Created by JeongMinCha on 16. 6. 16..
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.swmaestro.roundup.R;
import com.swmaestro.roundup.home.HomeFeedActivity;
import com.swmaestro.roundup.server_connector.ServerConfig;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.input_password_check) EditText _passwordCheckText;
    @InjectView(R.id.input_birth) EditText _birthText;
    @InjectView(R.id.input_phone_number) EditText _phoneNumberText;
    @InjectView(R.id.input_gender) EditText _genderButton;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(SignupActivity.this, HomeFeedActivity.class);
        intent.putExtra("user_name", _emailText.getText().toString());
        startActivity(intent);
        createUserIntoServer();
        finish();
    }

    private void createUserIntoServer() {
        String url = ServerConfig.BASE_URL + "user/";
        User user = new User();
        user.setEmail(_emailText.getText().toString());
        user.setName(_nameText.getText().toString());
        user.setPassword(_passwordText.getText().toString());
        user.setBirthDate(_birthText.getText().toString());
        user.setPhoneNumber(_phoneNumberText.getText().toString());
        if (_genderButton.getText().toString().equals('M')) {
            user.setGender(true);
        } else {
            user.setGender(false);
        }
        JSONObject object = user.getJsonObject();

        Log.i("all", object.toString());
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

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String passwordCheck = _passwordCheckText.getText().toString();
        String birthDate = _birthText.getText().toString();
        String phoneNumber = _phoneNumberText.getText().toString();
        Boolean gender = false;
        if (_genderButton.getText().toString().equals('M')) {
            gender = true;
        } else {
            gender = false;
        }

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (passwordCheck.isEmpty() || !passwordCheck.equals(password)) {
            _passwordCheckText.setError("Check password is different.");
            valid = false;
        } else {
            _passwordCheckText.setError(null);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(birthDate);
            if (!birthDate.equals(sdf.format(date))) {
                _birthText.setError("Birth date format is wrong. You should input it like yyyy-MM-dd");
                valid = false;
            }
        } catch (ParseException e) {
            _birthText.setError("Parse Exception");
            e.printStackTrace();
            valid = false;
        }

        return valid;
    }
}