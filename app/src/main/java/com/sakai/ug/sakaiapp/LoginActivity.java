package com.sakai.ug.sakaiapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sakai.ug.sakaiapp.APIservices.ApiClient;
import com.sakai.ug.sakaiapp.APIservices.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button btnLogin;
    private ApiInterface apiInterface;
    ApiClient apiClient = new ApiClient();
    private String jsession_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       if (SharedPreferencesManager.getInstance(this).isLoggedIn()) {
            //finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

    }

    private void UserLogin() {
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        //getting the user details
        final String _username = username.getText().toString().trim();
        final String _password = password.getText().toString().trim();


        apiInterface = apiClient.getApiClient().create(ApiInterface.class);
        Call<String> call = apiInterface.login(_username, _password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //hiding progress dialog
                progressDialog.dismiss();
                jsession_id = response.body();

                if (response.code() == 201) {
                    Log.d("Status", "onResponse: logged in");
                    SharedPreferencesManager.getInstance(getApplicationContext()).UserLogin(_username, _password, "no");
                    Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("Status", Integer.toString(response.code()));
                    Toast.makeText(getApplicationContext(), "Please check your credentials and try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("error", "onFailure: failed");
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(username.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_LONG).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_LONG).show();
        }
        else{
            UserLogin();
        }
    }


}
