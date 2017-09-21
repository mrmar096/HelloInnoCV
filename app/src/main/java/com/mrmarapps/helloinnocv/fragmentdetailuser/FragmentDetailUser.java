package com.mrmarapps.helloinnocv.fragmentdetailuser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragment;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentDetailUser extends BaseMVPFragment<FragmentDetailUserPresenter,FragmentDetailUserPresenter.Actions> {

    private UserDetail model;

    @Override
    public void inject(Context context) {
            DaggerFragmentDetailUserComponent.builder()
                    .fragmentDetailUserModule(new FragmentDetailUserModule(this))
                    .build().inject(this);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.user_detail_fragment;
    }

    public void setData(UserDetail model) {
        this.model = model;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(model != null){
            presenter.setData(model);
        }
    }
}
