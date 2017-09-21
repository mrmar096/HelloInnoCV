package com.mrmarapps.helloinnocv.mainactivity;

import android.view.Menu;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.di.ApiModule;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivity;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivity extends BaseActivity<MainActivityPresenter> {


    @Override
    protected void inject() {
        DaggerMainActivityComponent.builder()
                .injectedApplicationComponent(getApplicationComponent())
                .mainActivityModule(new MainActivityModule(this))
                .apiModule(new ApiModule(this))
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
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
