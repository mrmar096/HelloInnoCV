package com.mrmarapps.helloinnocv.mainactivity;

import android.support.v7.app.AppCompatActivity;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.di.activity.InjectedActivityModule;
import com.mrmarapps.helloinnocv.di.activity.PerActivity;
import com.mrmarapps.helloinnocv.di.application.InjectedApplicationComponent;
import com.mrmarapps.helloinnocv.di.fragment.InjectedFragment;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */

@PerActivity
@Component( dependencies = InjectedApplicationComponent.class,
            modules = MainActivityModule.class
)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
