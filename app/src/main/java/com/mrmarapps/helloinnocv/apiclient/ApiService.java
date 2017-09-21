package com.mrmarapps.helloinnocv.apiclient;

import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mario on 21/09/17.
 */

public class ApiService {

    public static InnocvApi generateApiService(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(InnocvApi.class);
    }
}
