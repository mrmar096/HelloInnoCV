package com.mrmarapps.helloinnocv.fragmentdetailuser;

import com.mrmarapps.helloinnocv.di.fragment.InjectedFragmentModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 14/09/17.
 */

@Module

public class FragmentDetailUserModule extends InjectedFragmentModule {

    public FragmentDetailUserModule(FragmentDetailUser fragment) {
        super(fragment);
    }

    @Provides
    public FragmentDetailUser proviFragmentDetailUser(){
        return (FragmentDetailUser) fragment;
    }
}
