package com.mrmarapps.helloinnocv.di.fragment;

import android.app.Application;
import android.content.Context;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.di.application.InjectedApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */
@PerFragment
@Component(modules = InjectedFragmentModule.class)
public interface InjectedFragmentComponent {


    void inject(InjectedFragment injectedFragment);

    Context context();

}
