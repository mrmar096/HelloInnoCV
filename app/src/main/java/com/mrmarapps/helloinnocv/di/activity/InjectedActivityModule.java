package com.mrmarapps.helloinnocv.di.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */

@Module
public class InjectedActivityModule {
    protected final InjectedActivity injectedActivity;


    public InjectedActivityModule(InjectedActivity injectedActivity) {
        this.injectedActivity = injectedActivity;
    }

    @Provides
    @PerActivity InjectedActivity provideInjectedActivity(){
        return injectedActivity;
    }

    @Provides
    @PerActivity
    Activity provideActivity(){
        return injectedActivity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideAppCompatActivityActivity(){
        return injectedActivity;
    }

}

