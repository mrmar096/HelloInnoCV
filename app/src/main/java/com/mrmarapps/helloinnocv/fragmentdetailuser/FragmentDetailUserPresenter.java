package com.mrmarapps.helloinnocv.fragmentdetailuser;

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
        this.userDetail =model;
        view.showDetailView();
        view.printUserDetail(model);
    }

    public void prepareNewUser() {
        view.showDetailView();
        view.clearFields();
    }


    public interface Actions extends PresenterActions {

    }
}
