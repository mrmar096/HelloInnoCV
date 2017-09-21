package com.mrmarapps.helloinnocv.detailactivity;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.activity.BaseActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mario on 12/09/17.
 */

public class DetailActivityView extends BaseActivityView<DetailActivity,DetailActivityView.Actions> implements FragmentDetailUserPresenter.Actions {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private final FragmentDetailUser fragmentDetailUser;

    @Inject
    public DetailActivityView(DetailActivity activity, FragmentDetailUser fragmentDetailUser) {
        super(activity);
        this.fragmentDetailUser = fragmentDetailUser;
    }

    @Override
    public void onInitView() {
        super.onInitView();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openFragment(R.id.container_detail,fragmentDetailUser);
        fragmentDetailUser.getPresenter().setListener(this);

    }

    @Override
    protected DetailActivityView.Actions getDefaultListener() {
        return  DetailActivityView.Actions.DEFAULT;
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

    public void showEmptyDetail() {
        fragmentDetailUser.getPresenter().showEmptyDetail();
    }

    public void undoDetailChanges() {
        fragmentDetailUser.getPresenter().undoDetailChanges();
    }

    public void prepareNewUser() {
        fragmentDetailUser.getPresenter().prepareNewUser();
    }

    @Override
    public void onSavedUserDetail(UserDetail userDetail) {
        actions.onUpdatedUser(userDetail);
    }

    @Override
    public void onSavedNewUserDetail(UserDetail userDetail) {
       actions.onPostNewUser(userDetail);
    }

    public void printData(UserDetail userDetail) {
        fragmentDetailUser.getPresenter().setData(userDetail);
    }

    public void askDeleteItem() {
        new MaterialDialog.Builder(activity)
                .title(R.string.ask_delete)
                .content(R.string.action_cant_undo)
                .negativeText(R.string.cancel)
                .positiveText(R.string.accept)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        actions.onDeleteItem();
                    }
                }).show();
    }

    public interface Actions extends ViewActions {
        Actions DEFAULT = new Actions() {

            @Override
            public void onPostNewUser(UserDetail userDetail) {

            }

            @Override
            public void onUpdatedUser(UserDetail userDetail) {

            }

            @Override
            public void onDeleteItem() {

            }
        };

        void onPostNewUser(UserDetail userDetail);

        void onUpdatedUser(UserDetail userDetail);

        void onDeleteItem();
    }
}
