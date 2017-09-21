package com.mrmarapps.helloinnocv.detailactivity;

import com.mrmarapps.helloinnocv.di.ApiModule;
import com.mrmarapps.helloinnocv.di.activity.PerActivity;
import com.mrmarapps.helloinnocv.di.application.InjectedApplicationComponent;

import dagger.Component;

/**
 * Created by mario on 11/09/17.
 */

@PerActivity
@Component( dependencies = InjectedApplicationComponent.class,
        modules ={
                DetailActivityModule.class,
                ApiModule.class
        }
)
public interface DetailActivityComponent {

    void inject(DetailActivity mainActivity);

}
