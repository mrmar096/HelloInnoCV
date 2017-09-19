package com.mrmarapps.helloinnocv.fragmentdetailuser;

import com.mrmarapps.helloinnocv.di.fragment.PerFragment;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */
@PerFragment
@Component(modules = FragmentDetailUserModule.class)
public interface FragmentDetailUserComponent {

    void inject(FragmentDetailUser fragmentDetailUser);
}
