package com.mrmarapps.helloinnocv.detailactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivity;

/**
 * Created by mario on 12/09/17.
 */

public class DetailActivity extends BaseActivity<DetailActivityPresenter> {


    public static final String ID_USER = "id_user";

    @Override
    protected void inject() {
        DaggerDetailActivityComponent.builder()
                .injectedApplicationComponent(getApplicationComponent())
                .detailActivityModule(new DetailActivityModule(this))
                .build().inject(this);

    }


    public static void launch(Activity activity,int idUser){
        Intent intent= new Intent(activity,DetailActivity.class);
        intent.putExtra(DetailActivity.ID_USER,idUser);
        ActivityCompat.startActivity(activity,intent,null);
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
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }
}
