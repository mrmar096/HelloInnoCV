package com.mrmarapps.helloinnocv.fragmentdetailuser;

import android.view.Menu;
import android.view.MenuInflater;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentPresenter;

import javax.inject.Inject;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentDetailUserPresenter extends BaseMVPFragmentPresenter<FragmentDetailUserView,FragmentDetailUser,FragmentDetailUserPresenter.Actions>
        implements FragmentDetailUserView.Actions{

    private UserDetail userDetail;

    @Inject
    public FragmentDetailUserPresenter(FragmentDetailUserView view, FragmentDetailUser fragment) {
        super(view, fragment);
    }

    public void setData(UserDetail model) {
        this.userDetail = model;
        view.showDetailView();
        view.printUserDetail(model);
    }

    public void prepareNewUser() {
        userDetail=null;
        view.showDetailView();
        view.clearFields();
    }

    public void showEmptyDetail() {
        view.hideDetailElement();
    }

    public void undoDetailChanges() {
        if(userDetail!=null){
            setData(userDetail);
        }
    }

    @Override
    public void onSaved(String birthDate, String name) {
        UserDetail userDetail = new UserDetail(name,birthDate);
        if(this.userDetail==null){
            actions.onSavedNewUserDetail(userDetail);
        }else{
            userDetail.setId(this.userDetail.getId());
            actions.onSavedUserDetail(userDetail);
        }

    }


    public interface Actions extends PresenterActions {

        void onSavedUserDetail(UserDetail userDetail);

        void onSavedNewUserDetail(UserDetail userDetail);
    }
}
