package com.mrmarapps.helloinnocv.mainactivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUser;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUserPresenter;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUserPresenter;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import javax.inject.Inject;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityPresenter extends BaseActivityPresenter<MainActivityView,MainActivity,MainActivityPresenter.Actions> implements MainActivityView.Actions, SearchView.OnQueryTextListener, FragmentListUserPresenter.Actions, FragmentDetailUserPresenter.Actions {

    private final FragmentListUser fragmentListUser;
    private final FragmentDetailUser fragmentDetailUser;
    private boolean isVisibleFragmentDetail;

    @Inject
    public MainActivityPresenter(MainActivityView view, MainActivity activity, FragmentListUser fragmentListUser, FragmentDetailUser fragmentDetailUser) {
        super(view, activity);
        this.fragmentListUser = fragmentListUser;
        this.fragmentDetailUser = fragmentDetailUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        openFragment(R.id.container_main,fragmentListUser);
        fragmentListUser.getPresenter().setListener(this);
        if(canOpenFrament(R.id.container_user_detail)){
            openFragment(R.id.container_user_detail,fragmentDetailUser);
            fragmentDetailUser.getPresenter().setListener(this);
            isVisibleFragmentDetail=true;
        }

    }

    @Override
    protected ViewActions getViewActions() {
        return this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();

        if(isVisibleFragmentDetail){
            menu.findItem(R.id.undo_item).setVisible(true);
            menu.findItem(R.id.delete_item).setVisible(true);
        }else{
            searchView.setMaxWidth(Integer.MAX_VALUE);
        }


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
        if(isVisibleFragmentDetail){
            fragmentDetailUser.getPresenter().prepareNewUser();
        }
    }

    @Override
    public void onRefreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentListUser.getPresenter().stopRefreshing();
            }
        },1000);
    }

    @Override
    public void onUserClicked(UserItem userItem) {
        if(isVisibleFragmentDetail){
            //Todo mapper userItem to UserDetail
            UserDetail userDetail= new UserDetail(userItem.getId(),userItem.getName(),userItem.getBirthDate());
            fragmentDetailUser.getPresenter().setData(userDetail);
        }
    }

    public interface Actions extends PresenterActions {

    }
}
