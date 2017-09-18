package com.mrmarapps.helloinnocv.mainactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivityModule;
import com.mrmarapps.helloinnocv.di.activity.PerActivity;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUserModule_ProvidesFragmentListUserFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */

@Module
public class MainActivityModule extends InjectedActivityModule {

    public MainActivityModule(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Provides
    public MainActivity provideMainActivity() {
        return (MainActivity) injectedActivity;
    }

    @Provides
    public FragmentListUser provideFragmentListUser(){
        return new FragmentListUser();
    }

}

