package com.mrmarapps.helloinnocv.di.application;

import android.app.Application;

/**
 * Created by mario on 11/09/17.
 */

public class InjectedApplication extends Application{

    private InjectedApplicationComponent injectedAplicationComponent;

    public InjectedApplicationComponent getInjectedAplicationComponent() {
        if(injectedAplicationComponent==null){
            injectedAplicationComponent = DaggerInjectedApplicationComponent.builder().
                    injectedApplicationModule(new InjectedApplicationModule(this))
                    .build();
        }
        return injectedAplicationComponent;
    }
}
