package com.mrmarapps.helloinnocv.di.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrmarapps.helloinnocv.di.activity.DaggerInjectedActivityComponent;
import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.di.activity.InjectedActivityComponent;
import com.mrmarapps.helloinnocv.di.activity.InjectedActivityModule;
import com.mrmarapps.helloinnocv.di.application.InjectedApplication;
import com.mrmarapps.helloinnocv.di.application.InjectedApplicationComponent;


public abstract class InjectedFragment extends Fragment {

    private InjectedFragmentModule injectedFragmentModule;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DaggerInjectedFragmentComponent.builder()
                .injectedFragmentModule(new InjectedFragmentModule(this))
                .build();

        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @LayoutRes
    public abstract int getFragmentLayout();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivityComponent().inject(this);
    }

    protected InjectedActivityComponent getActivityComponent() {
        return ((InjectedActivity) getActivity()).getActivityComponent();
    }

    protected InjectedFragmentModule getFragmentModule() {
        if (injectedFragmentModule == null) {
            injectedFragmentModule = new InjectedFragmentModule(this);
        }

        return injectedFragmentModule;
    }

}
