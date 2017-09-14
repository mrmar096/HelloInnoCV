package com.mrmarapps.helloinnocv.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mrmarapps.helloinnocv.di.fragment.InjectedFragment;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;

import javax.inject.Inject;

/**
 * Created by mario on 11/09/17.
 */

public abstract class BaseMVPFragment<PRESENTER extends BaseMVPFragmentPresenter, LISTENER extends PresenterActions>
        extends InjectedFragment {
    
    public LISTENER actions;
    @Inject
    protected PRESENTER presenter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        return inflater.inflate(getFragmentLayout(), container, false);
    }
    
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated(view);
        if (actions != null) {
            presenter.setListener(actions);
        }
    }
    
    @Override
    public void onAttach(Context context) {
        if (presenter == null) {
            inject(context);
        }
        presenter.setListener(actions);
        super.onAttach(context);
    }
    
    @Override
    public void onDestroyView() {
        presenter.onDestroyView();
        super.onDestroyView();
    }
    
    public abstract void inject(Context context);
    
    public PRESENTER getPresenter() {
        return presenter;
    }
    
    public void setListener(LISTENER actions) {
        this.actions = actions;
        if (presenter != null) {
            presenter.setListener(actions);
        }
    }
    
    public LISTENER getListener() {
        return actions;
    }
}
