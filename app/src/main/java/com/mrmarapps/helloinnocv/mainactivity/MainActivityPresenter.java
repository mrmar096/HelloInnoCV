package com.mrmarapps.helloinnocv.mainactivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;
import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.detailactivity.DetailActivity;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUser;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUserPresenter;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUserPresenter;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mainactivity.viewmodel.UserItemToUserDetail;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityPresenter extends BaseActivityPresenter<MainActivityView,MainActivity,MainActivityPresenter.Actions> implements MainActivityView.Actions, SearchView.OnQueryTextListener {


    private final InnocvApi innocvApi;

    @Inject
    public MainActivityPresenter(InnocvApi innocvApi,MainActivityView view, MainActivity activity) {
        super(view, activity);
        this.innocvApi = innocvApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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

        searchView.setMaxWidth(Integer.MAX_VALUE);



        searchView.setOnQueryTextListener(this);
        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        view.filterList(query);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete_item:
                view.showEmptyDetail();
                view.deleteItemSelected();
                break;
            case R.id.undo_item:
                view.undoDetailChanges();
                break;

        }


        return true;
    }

    @Override
    public void onUserShowDetail(UserItem userItem) {
        UserDetail userDetail = new UserItemToUserDetail().map(userItem);
        view.setDetailData(userDetail);
    }

    @Override
    public void onGoToDetail(int id) {
        DetailActivity.launch(activity,id);
    }

    public void onResume() {
        getAllUsers();
    }

    private void getAllUsers() {
        Observable<List<UserModel>> users = innocvApi.getUsers();
        users.subscribeOn(Schedulers.io());
        users.observeOn(AndroidSchedulers.mainThread());
        users.subscribe(new Subscriber<List<UserModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<UserModel> userModels) {
                view.showMessage("Lista cargada "+userModels);
            }
        });
    }

    public interface Actions extends PresenterActions {

    }
}
