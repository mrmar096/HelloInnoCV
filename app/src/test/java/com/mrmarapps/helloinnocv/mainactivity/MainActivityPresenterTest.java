package com.mrmarapps.helloinnocv.mainactivity;

import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;
import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.apiclient.request.UserRequest.UserRequest;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Observable;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mario on 22/09/2017.
 */
@RunWith(PowerMockRunner.class)

public class MainActivityPresenterTest {
    @Mock
    MainActivityPresenter presenter;

    @Mock
    MainActivityView view;

    @Mock
    MainActivity activity;

    @Mock
    InnocvApi innocvApi;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainActivityPresenter(innocvApi,view, activity);
    }


    @Test
    public void onPostNewUser() throws Exception {

        UserDetail userDetail = new UserDetail(1,"Mario","31/08/1996");

        when(innocvApi.postUser(mock(UserRequest.class))).thenReturn(rx.Observable.just(mock(UserModel.class)));


        presenter.onPostNewUser(userDetail);

        verify(view).showLoading();
    }
    @Test
    public void onUserShowDetail(){
        presenter.onUserShowDetail(mock(UserItem.class));
        verify(view).setDetailData(any(UserDetail.class));
    }
    @Test
    public void onOptionsDeleteItemSelected(){
        MenuItem menuItem = mock(MenuItem.class);
        doReturn(R.id.delete_item).when(menuItem).getItemId();
        presenter.onOptionsItemSelected(menuItem);
        verify(view).showEmptyDetail();
        verify(view).askDeleteItem();
    }
    @Test
    public void onOptionsUndoItemSelected(){
        MenuItem menuItem = mock(MenuItem.class);
        doReturn(R.id.undo_item).when(menuItem).getItemId();
        presenter.onOptionsItemSelected(menuItem);
        verify(view).undoDetailChanges();
    }
    @Test
    public void onQueryTextChange(){
        String query = anyString();
        presenter.onQueryTextChange(query);
        verify(view).filterList(query);
    }

}