package com.mrmarapps.helloinnocv.di.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mrmarapps.helloinnocv.di.application.InjectedApplication;
import com.mrmarapps.helloinnocv.di.application.InjectedApplicationComponent;


public abstract class InjectedActivity extends AppCompatActivity {

    private InjectedActivityModule injectedActivityModule;
    private InjectedActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent=DaggerInjectedActivityComponent.builder()
                .injectedActivityModule(new InjectedActivityModule(this))
                .injectedApplicationComponent(getApplicationComponent())
                .build();

        setContentView(getLayout());
    }



    protected abstract int getLayout();


    public InjectedApplicationComponent getApplicationComponent() {

        return ((InjectedApplication) getApplication()).getInjectedAplicationComponent();
    }

    public InjectedActivityModule getInjectedActivityModule() {

        if (injectedActivityModule == null) {
            injectedActivityModule = new InjectedActivityModule(this);
        }

        return injectedActivityModule;
    }

    public InjectedActivityComponent getActivityComponent() {

        return this.activityComponent;
    }
}
