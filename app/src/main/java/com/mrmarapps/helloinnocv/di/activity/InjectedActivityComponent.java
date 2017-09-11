package com.mrmarapps.helloinnocv.di.activity;

import android.support.v7.app.AppCompatActivity;

import com.mrmarapps.helloinnocv.di.application.InjectedApplicationComponent;
import com.mrmarapps.helloinnocv.di.application.InjectedApplicationModule;
import com.mrmarapps.helloinnocv.di.fragment.InjectedFragment;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */

@PerActivity
@Component( dependencies = InjectedApplicationComponent.class,
            modules = InjectedActivityModule.class)
public interface InjectedActivityComponent {

    void inject(InjectedActivity injectedActivity);

    void inject(InjectedFragment injectedFragment);

    AppCompatActivity appCompatActivity();

    InjectedActivity injectedActivity();
}
