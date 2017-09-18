package com.mrmarapps.helloinnocv.fragmentlistuser;

import android.content.Context;

import com.mrmarapps.helloinnocv.di.fragment.InjectedFragment;
import com.mrmarapps.helloinnocv.di.fragment.InjectedFragmentModule;
import com.mrmarapps.helloinnocv.di.fragment.PerFragment;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */
@PerFragment
@Component(modules = FragmentListUserModule.class)
public interface FragmentListUserComponent {

    void inject(FragmentListUser fragmentListUser);
}
