package com.mrmarapps.helloinnocv.mvp.activity;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.di.activity.InjectedActivity;
import com.mrmarapps.helloinnocv.mvp.ViewActions;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mario on 11/09/17.
 */

public abstract class BaseActivityView<ACTIVITY extends InjectedActivity,LISTENER extends ViewActions> {

    protected ACTIVITY activity;
    protected LISTENER actions;

    private Unbinder unbinder;

    public BaseActivityView(ACTIVITY activity) {
        this.activity = activity;
    }

    public void onInitView() {
        unbinder = ButterKnife.bind(this, activity);
    }

    public void setViewActionsListener(LISTENER viewActionsListener) {
        if (viewActionsListener != null) {
            this.actions = viewActionsListener;
        } else {
            this.actions = getDefaultListener();
        }
    }

    protected abstract LISTENER getDefaultListener();

    protected String getString(int resourceString){
        return activity.getString(resourceString);
    }


    protected void onDestroy() {
        unbinder.unbind();
    }

    protected boolean canOpenFrament(@IdRes int container) {
        return activity.findViewById(container)!=null;
    }

    protected void openFragment(@IdRes int idContainer, Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(idContainer, fragment)
                .commitNowAllowingStateLoss();

    }

    public void showErrorConnection() {
        Toast.makeText(activity, R.string.error_conection, Toast.LENGTH_SHORT).show();
    }

    public void showError(String messageError) {
        Toast.makeText(activity, messageError, Toast.LENGTH_LONG).show();
    }
    public void showError(@StringRes int stringResource) {
        Toast.makeText(activity, stringResource, Toast.LENGTH_LONG).show();
    }

    public void showMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
    public void showMessage(@StringRes int stringResource) {
        Toast.makeText(activity, stringResource, Toast.LENGTH_SHORT).show();
    }

    public void showErrorOcurred() {
        showError(R.string.error_ocurred);
    }



}
