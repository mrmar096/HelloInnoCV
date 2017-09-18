package com.mrmarapps.helloinnocv.di.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */

@Module
public class InjectedFragmentModule {
    protected final Fragment fragment;

    public InjectedFragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides @PerFragment
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    FragmentActivity provideFragmentActivity() {
        return fragment.getActivity();
    }


}

