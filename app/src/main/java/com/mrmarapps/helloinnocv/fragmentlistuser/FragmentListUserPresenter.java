package com.mrmarapps.helloinnocv.fragmentlistuser;

import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.PresenterActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentListUserPresenter extends BaseMVPFragmentPresenter<FragmentListUserView,FragmentListUser,FragmentListUserPresenter.Actions>
        implements FragmentListUserView.Actions{

    private List<UserItem> data;

    @Inject
    public FragmentListUserPresenter(FragmentListUserView view, FragmentListUser fragment) {
        super(view, fragment);
    }

    public void setData(List<UserItem> data) {
        this.data=data;
        view.setList(data);
    }

    public void filterList(String query) {
        view.filterList(query);
    }


    public interface Actions extends PresenterActions {
    }
}
