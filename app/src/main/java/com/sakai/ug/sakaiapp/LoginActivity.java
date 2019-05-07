package com.sakai.ug.sakaiapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaderFactory;
import com.bumptech.glide.load.model.LazyHeaders;
import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.LoginSessionInterface;
import com.sakai.ug.sakaiapp.APIservices.ProfileInterface;
import com.sakai.ug.sakaiapp.models.profile.Profile;
import com.sakai.ug.sakaiapp.models.session.Session;

import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //login form views
    private EditText username;
    private EditText password;
    private Button btnLogin;

    //Retrofit stuff
    private LoginSessionInterface loginSessionInterface;
    private ProfileInterface profileInterface;
    ApiClient apiClient = new ApiClient();
    private Session session = new Session();
    private Profile profile = new Profile();
    private String jsession_id;

    //other necessary stuff
    private String userId;
    private String displayname;
    private String email;
    private String image_url;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //defining a progress dialog to show while signing up
        progressDialog = new ProgressDialog(this);

        //take the user straight to the main activity if already logged in
        if (SharedPreferencesManager.getInstance(this).isLoggedIn()) {
            finish();
            overridePendingTransition(0, 0);
            startActivity(new Intent(this, MainActivity.class));
        }

        //create instances of the interface to be used to make retrofit calls
        loginSessionInterface = apiClient.getApiClient(getApplicationContext()).create(LoginSessionInterface.class);
        profileInterface = apiClient.getApiClient(getApplicationContext()).create(ProfileInterface.class);

        //initialize the UI views
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    private void UserLogin() {
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        //getting the user details
        final String _username = username.getText().toString().trim();
        final String _password = password.getText().toString().trim();

        Call<String> call = loginSessionInterface.login(_username, _password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                jsession_id = response.body();

                if (response.code() == 201) {
                    Log.d("Status", "onResponse: logged in");
                    SharedPreferencesManager.getInstance(getApplicationContext()).UserLogin(_username, _password, "no");
                    UserSessionDetails();
                    Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_LONG).show();

                } else {
                    Log.d("Status", Integer.toString(response.code()));
                    Toast.makeText(getApplicationContext(), "Please check your credentials and try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Login fail", "onFailure: failed");
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void UserSessionDetails() {
        Call<Session> call1 = loginSessionInterface.getSessionDetails();

        call1.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Session status", "onResponse: Retrieving session details");
                    session = response.body();
                    userId = session.getUserId();
                    Log.d("Response", "onResponse: " + userId);
                    SharedPreferencesManager.getInstance(getApplicationContext()).UserSession(userId);
                    getUserProfileDetails();
                } else {
                    Log.d("Response", "onResponse: Error");
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Log.d("User session fail", "onFailure: " + t.getMessage());
            }
        });
    }


    private void getUserProfileDetails() {

        Call<Profile> call2 = profileInterface.getUserProfileDetails(SharedPreferencesManager.getInstance(getApplicationContext()).getUSERID());
        call2.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();

                    Log.d("Profile status", "onResponse: Retrieving user profile details");
                    profile = response.body();
                    displayname = profile.getDisplayName();
                    email = profile.getEmail();
                    Log.d("Name", "onResponse:" + displayname + " " + email);
                    image_url = profile.getImageUrl();
                    SharedPreferencesManager.getInstance(getApplicationContext()).UserProfileDetails(displayname, email, image_url);


                    //start the main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Log.d("Response", "onResponse: Error");
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("User profile fail", "onFailure: " + t.getMessage());
            }
        });
    }



    @Override
    public void onClick(View v) {
        if (username.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_LONG).show();
        } else if (password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
        } else {
            UserLogin();
        }
    }


    //when access to internet isn't possible
    private void UserLogin2() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*@Override
    public void onClick(View v) {
        UserLogin2();
    }*/


}



