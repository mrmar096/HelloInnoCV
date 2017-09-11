package com.mrmarapps.helloinnocv.di.application;

import android.app.Application;
import android.content.Context;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */
@Singleton
@Component(modules = InjectedApplicationModule.class)
public interface InjectedApplicationComponent {


    void inject(InjectedActivity injectedActivity);

    Context context();

    Application getApplication();

}
