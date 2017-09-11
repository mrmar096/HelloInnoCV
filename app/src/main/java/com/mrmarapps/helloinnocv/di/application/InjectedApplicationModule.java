package com.mrmarapps.helloinnocv.di.application;

import android.app.Application;
import android.content.Context;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */
@Module
public class InjectedApplicationModule {

    private final Application application;

    public InjectedApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return application;
    }
    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }


}
