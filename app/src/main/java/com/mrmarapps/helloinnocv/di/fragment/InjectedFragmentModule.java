package com.mrmarapps.helloinnocv.di.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.di.activity.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */

@Module
public class InjectedFragmentModule {
    protected final InjectedFragment injectedFragment;


    public InjectedFragmentModule(InjectedFragment injectedFragment) {
        this.injectedFragment = injectedFragment;
    }

    @Provides
    @PerFragment InjectedFragment provideInjectedFragment(){
        return injectedFragment;
    }

    @Provides
    @PerFragment
    Context provideContext(){
        return injectedFragment.getContext();
    }


}

