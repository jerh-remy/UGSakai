package com.sakai.ug.sakaiapp.APIservices;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://sakaiapp.ngrok.io/direct/";
    public Retrofit retrofit = null;
    public OkHttpClient okHttpClient = null;
    private Context context = null;


    public OkHttpClient getOkHttpClient(Context context)
    {
        SendSavedCookiesInterceptor sendSavedCookiesInterceptor = new SendSavedCookiesInterceptor(context);
        SaveReceivedCookiesInterceptor saveReceivedCookiesInterceptor = new SaveReceivedCookiesInterceptor(context);

        return new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .addInterceptor(sendSavedCookiesInterceptor)
                .addInterceptor(saveReceivedCookiesInterceptor)
                .build();

    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

        public Retrofit getApiClient(Context context) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
