package com.mrmarapps.helloinnocv.mainactivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivity;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivity extends BaseActivity<MainActivityPresenter> {


    @Override
    protected void inject() {
        DaggerMainActivityComponent.builder()
                .injectedApplicationComponent(getApplicationComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return presenter.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
