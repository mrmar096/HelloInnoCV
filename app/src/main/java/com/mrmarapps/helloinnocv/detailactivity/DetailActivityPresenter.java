package com.mrmarapps.helloinnocv.detailactivity;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import javax.inject.Inject;

/**
 * Created by mario on 12/09/17.
 */

public class DetailActivityPresenter extends BaseActivityPresenter<DetailActivityView,DetailActivity,DetailActivityPresenter.Actions> implements DetailActivityView.Actions {


    private int idUser;

    @Inject
    public DetailActivityPresenter(DetailActivityView view, DetailActivity activity) {
        super(view, activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        idUser = activity.getIntent().getIntExtra(DetailActivity.ID_USER, -1);

    }

    @Override
    protected ViewActions getViewActions() {
        return this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_detail, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            case R.id.delete_item:
                askDeleteItem();
                break;
            case R.id.undo_item:
                view.undoDetailChanges();
                break;
        }


        return true;
    }

    public void onBackPressed() {
        activity.finish();
    }

    private void askDeleteItem() {

    }

    public void onResume() {
        if(idUser==-1){
            view.prepareNewUser();
            return;
        }
        //Todo get data from server
    }

    public interface Actions extends PresenterActions {

    }
}
