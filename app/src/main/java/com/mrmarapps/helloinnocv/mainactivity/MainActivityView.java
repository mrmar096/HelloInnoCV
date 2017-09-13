package com.mrmarapps.helloinnocv.mainactivity;

import android.support.v7.widget.Toolbar;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityView extends BaseActivityView<MainActivity,MainActivityView.Actions> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    public MainActivityView(MainActivity activity) {
        super(activity);
    }

    @Override
    public void onInitView() {
        super.onInitView();
        activity.setSupportActionBar(toolbar);
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
