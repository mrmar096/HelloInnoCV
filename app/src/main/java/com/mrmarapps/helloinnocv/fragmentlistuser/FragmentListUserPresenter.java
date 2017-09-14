package com.mrmarapps.helloinnocv.fragmentlistuser;

import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.User;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragment;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentPresenter;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentView;

import java.util.List;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentListUserPresenter extends BaseMVPFragmentPresenter {

    private List<User> data;

    public FragmentListUserPresenter(BaseMVPFragmentView view, BaseMVPFragment fragment) {
        super(view, fragment);
    }

    public void setData(List<User> data) {
        this.data=data;
    }


    public class Actions implements PresenterActions {
    }
}
