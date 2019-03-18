package com.sakai.ug.sakaiapp.APIservices;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sakai.ug.sakaiapp.interceptors.AddCookiesInterceptor;
import com.sakai.ug.sakaiapp.interceptors.ReceivedCookiesInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = " http://c7181ecf.ngrok.io/direct/";
    public Retrofit retrofit = null;
    public OkHttpClient okHttpClient;
    private Context context;


    public OkHttpClient getOkHttpClient() {

        okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new AddCookiesInterceptor(context));
        builder.addInterceptor(new ReceivedCookiesInterceptor(context));
        okHttpClient = builder.build();
        return okHttpClient;
    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
