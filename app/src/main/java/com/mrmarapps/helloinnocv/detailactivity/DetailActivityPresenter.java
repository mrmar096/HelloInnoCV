package com.mrmarapps.helloinnocv.detailactivity;

import android.view.Menu;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.apiclient.apicalls.BaseModel;
import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;
import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.apiclient.request.UserRequest.UserRequest;
import com.mrmarapps.helloinnocv.detailactivity.domain.mapper.UserDetailToUserRequest;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.mapper.UserModelToUserDetail;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityPresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mario on 12/09/17.
 */

public class DetailActivityPresenter extends BaseActivityPresenter<DetailActivityView,DetailActivity,DetailActivityPresenter.Actions> implements DetailActivityView.Actions {


    private int idUser;
    private final InnocvApi innocvApi;

    @Inject
    public DetailActivityPresenter(DetailActivityView view, DetailActivity activity, InnocvApi innocvApi) {
        super(view, activity);
        this.innocvApi = innocvApi;
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


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            case R.id.delete_item:
                view.askDeleteItem();
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

    @Override
    public void onDeleteItem() {
        if(idUser==-1){
            return;
        }
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
                        activity.finish();
                    }

                    @Override
                    public void onNext(BaseModel userModel) {
                        //If entry Here means that api show Message Error
                        view.showMessage(R.string.error_ocurred);

                    }
                })
        ;
    }

    public void onResume() {
        if(idUser==-1){
            view.prepareNewUser();
            return;
        }
        view.showLoading();
        innocvApi.getUser(String.valueOf(idUser))
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<UserModel>() {
            @Override
            public void onCompleted() {
                view.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                view.showError(R.string.error_ocurred);
                view.hideLoading();
            }

            @Override
            public void onNext(UserModel userModel) {
                UserDetail userDetail = new UserModelToUserDetail().map(userModel);
                view.printData(userDetail);
            }
        })
        ;



    }

    @Override
    public void onPostNewUser(UserDetail userDetail) {
        UserRequest userRequest = new UserDetailToUserRequest().map(userDetail);
        innocvApi.postUser(userRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(R.string.error_ocurred);
                        view.hideLoading();
                    }

                    @Override
                    public void onNext(UserModel userModels) {
                        UserDetail newUser = new UserModelToUserDetail().map(userModels);
                        view.showMessage(R.string.user_added);
                        view.printData(newUser);
                    }
                });
    }

    @Override
    public void onUpdatedUser(UserDetail userDetail) {
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
                        UserDetail newUser = new UserModelToUserDetail().map(userModels);
                        view.showMessage(R.string.user_updated);
                        view.printData(newUser);
                    }
                });
    }

    public void onCreateOptionsMenu(Menu menu) {
        if(idUser==-1){
            menu.findItem(R.id.delete_item).setVisible(false);
            menu.findItem(R.id.undo_item).setVisible(false);
        }
    }

    public interface Actions extends PresenterActions {

    }
}
