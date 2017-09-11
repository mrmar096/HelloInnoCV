package com.mrmarapps.helloinnocv.mvp.activity;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;

/**
 * Created by mario on 11/09/17.
 */

public abstract class BaseActivityPresenter<VIEW extends BaseActivityView,ACTIVITY extends InjectedActivity,LISTENER extends PresenterActions> {
    protected final VIEW view;
    protected final ACTIVITY activity;
    protected  LISTENER actions;


    public BaseActivityPresenter(VIEW view, ACTIVITY activity) {
        this.view = view;
        this.activity = activity;
    }

    public void setListener(LISTENER listener) {
        if (listener != null) {
            this.actions = listener;
        }
    }

    public void onCreate() {
        view.setViewActionsListener(getViewActions());
        view.onInitView();
    }

    protected abstract ViewActions getViewActions();


    public void onDestroy() {
        view.onDestroy();
    }

    protected String getString(int resourceString){
        return activity.getString(resourceString);
    }


}
