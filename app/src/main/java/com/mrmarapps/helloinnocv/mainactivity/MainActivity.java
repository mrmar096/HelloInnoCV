package com.mrmarapps.helloinnocv.mainactivity;

import android.os.Bundle;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivity;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivity extends BaseActivity<MainActivityPresenter> {


    @Override
    protected void inject() {


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
