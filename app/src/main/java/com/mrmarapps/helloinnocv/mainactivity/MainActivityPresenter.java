package com.mrmarapps.helloinnocv.mainactivity;

import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import javax.inject.Inject;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityPresenter extends BaseActivityPresenter<MainActivityView,MainActivity,MainActivityPresenter.Actions> implements MainActivityView.Actions {

    @Inject
    public MainActivityPresenter(MainActivityView view, MainActivity activity) {
        super(view, activity);
    }

    @Override
    protected ViewActions getViewActions() {
        return view.getDefaultListener();
    }

    public interface Actions extends PresenterActions {

    }
}
