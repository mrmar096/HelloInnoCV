package com.mrmarapps.helloinnocv.mvp.activity;

import android.os.Bundle;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;

import javax.inject.Inject;

/**
 * Created by mario on 11/09/17.
 */

public abstract class BaseActivity<PRESENTER extends BaseActivityPresenter> extends InjectedActivity {

    @Inject
    protected PRESENTER presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public PRESENTER getPresenter() {
        return presenter;
    }

    protected abstract void inject();
}
