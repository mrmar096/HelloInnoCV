package com.mrmarapps.helloinnocv.di.fragment;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */

@Module
public class InjectedFragmentModule {
    protected final InjectedFragment fragment;


    public InjectedFragmentModule(InjectedFragment injectedFragment) {
        this.fragment = injectedFragment;
    }

    @Provides
    @PerFragment InjectedFragment provideInjectedFragment(){
        return fragment;
    }

    @Provides
    @PerFragment
    Context provideContext(){
        return fragment.getContext();
    }


}

