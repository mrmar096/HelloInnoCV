package com.mrmarapps.helloinnocv.mainactivity;

import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

import javax.inject.Inject;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityView extends BaseActivityView<MainActivity,MainActivityView.Actions> {

    @Inject
    public MainActivityView(MainActivity activity) {
        super(activity);
    }

    @Override
    protected MainActivityView.Actions getDefaultListener() {
        return MainActivityView.Actions.DEFAULT;
    }

    public interface Actions extends ViewActions {
        Actions DEFAULT = new Actions() {

        };
    }
}
