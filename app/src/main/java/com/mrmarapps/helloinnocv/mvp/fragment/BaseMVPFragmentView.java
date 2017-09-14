package com.mrmarapps.helloinnocv.mvp.fragment;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.mvp.ViewActions;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMVPFragmentView<FRAGMENT extends BaseMVPFragment, LISTENER extends ViewActions> {
    protected FRAGMENT fragment;
    protected LISTENER actions;

    private Unbinder unbinder;

    public BaseMVPFragmentView(FRAGMENT fragment) {
        this.fragment = fragment;
    }

    public void bind(LISTENER actions, View view) {
        this.actions = actions;

        unbinder = ButterKnife.bind(this, view);
        initView();
    }

    public abstract void initView();

    public void onDestroyView() {
        unbinder.unbind();
    }
    
    protected String getString(@StringRes int stringRes) {
        return fragment.getString(stringRes);
    }
       
    
    public void showMessage(String message) {
        Toast.makeText(fragment.getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    public void showMessage(@StringRes int stringResource) {
        Toast.makeText(fragment.getActivity(), stringResource, Toast.LENGTH_SHORT).show();
    }

    public void showErrorConnection() {
        showMessage(R.string.error_conection);
    }

    public void showErrorOcurred() {
        showError(R.string.error_ocurred);
    }

    public void showError(@StringRes int stringResource) {
        Toast.makeText(fragment.getActivity(), stringResource, Toast.LENGTH_SHORT).show();
    }
}
