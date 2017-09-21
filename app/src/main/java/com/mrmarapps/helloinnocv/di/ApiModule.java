package com.mrmarapps.helloinnocv.di;

import android.content.Context;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.apiclient.ApiService;
import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 21/09/17.
 */

@Module
public class ApiModule {
    private final Context context;

    public ApiModule(Context context) {
        this.context = context;
    }

    @Provides
    InnocvApi providerInnocvApi() {
        return ApiService.generateApiService(context.getString(R.string.end_point));
    }
}
