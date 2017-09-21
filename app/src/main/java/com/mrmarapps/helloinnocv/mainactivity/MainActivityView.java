package com.mrmarapps.helloinnocv.mainactivity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUser;
import com.mrmarapps.helloinnocv.fragmentdetailuser.FragmentDetailUserPresenter;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUser;
import com.mrmarapps.helloinnocv.fragmentlistuser.FragmentListUserPresenter;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mario on 12/09/17.
 */

public class MainActivityView extends BaseActivityView<MainActivity,MainActivityView.Actions> implements FragmentListUserPresenter.Actions, FragmentDetailUserPresenter.Actions {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private final FragmentListUser fragmentListUser;
    private final FragmentDetailUser fragmentDetailUser;

    private boolean isVisibleFragmentDetail;


    @Inject
    public MainActivityView(MainActivity activity, FragmentListUser fragmentListUser, FragmentDetailUser fragmentDetailUser) {
        super(activity);
        this.fragmentListUser = fragmentListUser;
        this.fragmentDetailUser = fragmentDetailUser;
    }

    @Override
    public void onInitView() {
        super.onInitView();
        activity.setSupportActionBar(toolbar);

        openFragment(R.id.container_main,fragmentListUser);
        fragmentListUser.getPresenter().setListener(this);
        if(canOpenFrament(R.id.container_user_detail)){
            openFragment(R.id.container_user_detail,fragmentDetailUser);
            fragmentDetailUser.getPresenter().setListener(this);
            isVisibleFragmentDetail=true;
        }

    }


    @OnClick(R.id.fab)
    public void onAddUser(){
        if(isVisibleFragmentDetail){
            showDeleteAction(false);
            showUndoAction(true);
            fragmentDetailUser.getPresenter().prepareNewUser();
        }else{
            actions.onGoToDetail(-1);
        }
    }

    @Override
    public void onSavedUserDetail(UserDetail userDetail) {

        actions.onUpdateUser(userDetail);

    }

    @Override
    public void onSavedNewUserDetail(UserDetail userDetail) {
        actions.onPostNewUser(userDetail);
    }

    @Override
    protected MainActivityView.Actions getDefaultListener() {
        return MainActivityView.Actions.DEFAULT;
    }

    public void showActionsToolbar() {
        showUndoAction(true);
        showDeleteAction(true);
    }
    public void hideActionsToolbar() {
        showUndoAction(false);
        showDeleteAction(false);
    }

    private MenuItem showDeleteAction(boolean value) {
        return toolbar.getMenu().findItem(R.id.delete_item).setVisible(value);
    }

    private void showUndoAction(boolean value) {
        toolbar.getMenu().findItem(R.id.undo_item).setVisible(value);
    }



    @Override
    public void onRefreshData() {
     actions.onRefreshList();
    }

    public void stopRefreshing(){
        fragmentListUser.getPresenter().stopRefreshing();
    }
    @Override
    public void onUserClicked(UserItem userItem) {
        if(isVisibleFragmentDetail){
            showActionsToolbar();
            actions.onUserShowDetail(userItem);

        }else{
            actions.onGoToDetail(userItem.getId());
        }
    }

    public void filterList(String query) {
        fragmentListUser.getPresenter().filterList(query);
    }

    public void deleteItemSelected() {
        fragmentListUser.getPresenter().deleteItemSelected();
    }

    public void showEmptyDetail() {
        fragmentDetailUser.getPresenter().showEmptyDetail();
    }

    public void undoDetailChanges() {
        fragmentDetailUser.getPresenter().undoDetailChanges();
    }

    public void setDetailData(UserDetail userDetail) {
        fragmentDetailUser.getPresenter().setData(userDetail);
    }

    public void displayUsers(ArrayList<UserItem> userItems) {
        fragmentListUser.getPresenter().setData(userItems);
    }

    public void addUserToList(UserItem userItem) {
        fragmentListUser.getPresenter().addUserItem(userItem);
    }

    public void askDeleteItem() {
        final int idElementSelected = fragmentListUser.getPresenter().getIdElementSelected();
        new MaterialDialog.Builder(activity)
                .title(R.string.ask_delete)
                .content(R.string.action_cant_undo)
                .negativeText(R.string.cancel)
                .positiveText(R.string.accept)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        actions.onDeleteItem(idElementSelected);
                    }
                }).show();
    }

    public void setUserToList(UserItem newUser, UserItem oldUserItem) {
        fragmentListUser.getPresenter().updateUser(newUser,oldUserItem);
    }


    public interface Actions extends ViewActions {
        Actions DEFAULT = new Actions() {

            @Override
            public void onGoToDetail(int id) {

            }

            @Override
            public void onUserShowDetail(UserItem userItem) {

            }

            @Override
            public void onRefreshList() {

            }

            @Override
            public void onPostNewUser(UserDetail userDetail) {

            }

            @Override
            public void onUpdateUser(UserDetail userDetail) {

            }

            @Override
            public void onDeleteItem(int idElementSelected) {

            }
        };

        void onGoToDetail(int id);

        void onUserShowDetail(UserItem userItem);

        void onRefreshList();

        void onPostNewUser(UserDetail userDetail);

        void onUpdateUser(UserDetail userDetail);

        void onDeleteItem(int idElementSelected);
    }
}
