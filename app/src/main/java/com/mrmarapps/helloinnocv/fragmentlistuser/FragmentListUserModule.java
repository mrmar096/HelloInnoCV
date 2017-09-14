package com.mrmarapps.helloinnocv.fragmentlistuser;

import com.mrmarapps.helloinnocv.di.fragment.InjectedFragmentModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 14/09/17.
 */

@Module

public class FragmentListUserModule extends InjectedFragmentModule {

    public FragmentListUserModule(FragmentListUser fragment) {
        super(fragment);
    }

    @Provides
    public FragmentListUser providesFragmentListUser(){
        return (FragmentListUser) fragment;
    }
}
