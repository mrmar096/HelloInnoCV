package com.mrmarapps.helloinnocv.mainactivity;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUserPresenter;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import javax.inject.Inject;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityPresenter extends BaseActivityPresenter<MainActivityView,MainActivity,MainActivityPresenter.Actions> implements MainActivityView.Actions, SearchView.OnQueryTextListener, FragmentListUserPresenter.Actions {

    private final FragmentListUser fragmentListUser;

    @Inject
    public MainActivityPresenter(MainActivityView view, MainActivity activity, FragmentListUser fragmentListUser) {
        super(view, activity);
        this.fragmentListUser = fragmentListUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        openFragment(R.id.container_main,fragmentListUser);
        fragmentListUser.getPresenter().setListener(this);

    }

    @Override
    protected ViewActions getViewActions() {
        return this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(activity.getComponentName()));

        searchView.setOnQueryTextListener(this);
        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        fragmentListUser.getPresenter().filterList(query);
        return true;
    }

    @Override
    public void onAddUser() {
        view.showMessage("ADD USER");
    }

    public interface Actions extends PresenterActions {

    }
}
