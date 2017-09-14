package com.mrmarapps.helloinnocv.fragmentlistuser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.User;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragment;

import java.util.List;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentListUser extends BaseMVPFragment<FragmentListUserPresenter,FragmentListUserPresenter.Actions> {

    private List<User> data;

    @Override
    public void inject(Context context) {

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.user_list_fragment;
    }

    public void setData(List<User> model) {
        this.data = model;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(data != null){
            presenter.setData(data);
        }
    }
}
