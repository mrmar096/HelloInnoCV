package com.mrmarapps.helloinnocv.mvp.fragment;

import android.view.View;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;

/**
 * Created by mario on 11/09/17.
 */


public abstract class BaseMVPFragmentPresenter<VIEW extends BaseMVPFragmentView, FRAGMENT extends BaseMVPFragment, LISTENER extends PresenterActions>
    implements ViewActions {
    protected final VIEW view;
    protected final FRAGMENT fragment;
    protected LISTENER actions;

    public BaseMVPFragmentPresenter(VIEW view, FRAGMENT fragment) {
        this.view = view;
        this.fragment = fragment;
        actions = getDefaultActions() != null ? getDefaultActions() : (LISTENER) PresenterActions.DEFAULT;
    }
    protected LISTENER getDefaultActions() {
        return null;
    }
    
    public void setListener(LISTENER listener) {
        if (listener != null) {
            this.actions = listener;
        }
    }

    public void onViewCreated(View view) {
        this.view.bind(this, view);
    }

    public void onDestroyView() {
        view.onDestroyView();
    }
    
    protected InjectedActivity getActivity() {
        return (InjectedActivity) fragment.getActivity();
    }
    
    protected void showMessageAccordingError(HelloInnoCvException e) {

        
        view.showErrorOcurred();
        
    }



}
