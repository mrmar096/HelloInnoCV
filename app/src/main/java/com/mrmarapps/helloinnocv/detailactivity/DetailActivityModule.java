package com.mrmarapps.helloinnocv.detailactivity;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivityModule;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mario on 11/09/17.
 */

@Module
public class DetailActivityModule extends InjectedActivityModule {

    public DetailActivityModule(DetailActivity mainActivity) {
        super(mainActivity);
    }

    @Provides
    public DetailActivity provideMainActivity() {
        return (DetailActivity) injectedActivity;
    }

    @Provides
    public FragmentDetailUser provideFragmentDetailUser(){
        return new FragmentDetailUser();
    }

}

