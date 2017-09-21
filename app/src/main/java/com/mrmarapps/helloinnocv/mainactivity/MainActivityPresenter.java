package com.mrmarapps.helloinnocv.mainactivity;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.apiclient.apicalls.BaseModel;
import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;
import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.apiclient.request.UserRequest.UserRequest;
import com.mrmarapps.helloinnocv.detailactivity.DetailActivity;
import com.mrmarapps.helloinnocv.detailactivity.domain.mapper.UserDetailToUserRequest;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mainactivity.domain.mapper.UserModelToUserItem;
import com.mrmarapps.helloinnocv.mainactivity.viewmodel.UserItemToUserDetail;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
                view.askDeleteItem();
                break;
            case R.id.undo_item:
                view.undoDetailChanges();
                break;

        }


        return true;
    }

    @Override
    public void onDeleteItem(int idUser) {
        innocvApi.removeUser(String.valueOf(idUser))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Here Because Api return empty on delete
                        view.showMessage(R.string.user_deleted);
                        view.deleteItemSelected();
                    }

                    @Override
                    public void onNext(BaseModel userModel) {
                        //If entry Here means that api show Message Error
                        view.showMessage(R.string.error_ocurred);
                    }
                })
        ;
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
        view.showLoading();
        innocvApi.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserModel>>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                        view.stopRefreshing();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(R.string.error_ocurred);
                        view.hideLoading();
                        view.stopRefreshing();
                    }

                    @Override
                    public void onNext(List<UserModel> userModels) {
                        ArrayList<UserItem> userItems = (ArrayList<UserItem>) new UserModelToUserItem().map(userModels);
                        view.displayUsers(userItems);
                    }
                });

    }

    @Override
    public void onPostNewUser(UserDetail userDetail) {
        UserRequest userRequest = new UserDetailToUserRequest().map(userDetail);
        view.showLoading();
        innocvApi.postUser(userRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserModel>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(R.string.error_ocurred);
                        view.hideLoading();
                        view.stopRefreshing();
                    }

                    @Override
                    public void onNext(UserModel userModels) {
                        UserItem userItem = new UserModelToUserItem().map(userModels);
                        view.addUserToList(userItem);
                    }
                });

    }

    @Override
    public void onUpdateUser(UserDetail userDetail) {
        final UserItem oldUserItem = new UserItemToUserDetail().reverseMap(userDetail);
        UserRequest userRequest = new UserDetailToUserRequest().map(userDetail);
        innocvApi.updateUser(userRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(R.string.error_ocurred);
                    }

                    @Override
                    public void onNext(UserModel userModels) {
                        UserItem newUser = new UserModelToUserItem().map(userModels);
                        view.showMessage(R.string.user_updated);
                        view.setUserToList(newUser,oldUserItem);
                    }
                });
    }

    @Override
    public void onRefreshList() {
        getAllUsers();
    }

    public interface Actions extends PresenterActions {

    }
}
