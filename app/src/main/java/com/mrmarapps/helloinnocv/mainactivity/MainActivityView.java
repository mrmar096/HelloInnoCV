package com.mrmarapps.helloinnocv.mainactivity;

import android.support.v7.widget.Toolbar;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUserPresenter;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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


    @OnClick(R.id.fab)
    public void onAddUser(){
        actions.onAddUser();
    }



    @Override
    protected MainActivityView.Actions getDefaultListener() {
        return MainActivityView.Actions.DEFAULT;
    }




    public interface Actions extends ViewActions {
        Actions DEFAULT = new Actions() {

            @Override
            public void onAddUser() {

            }
        };

        void onAddUser();

    }
}
