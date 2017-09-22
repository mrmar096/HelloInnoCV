package com.mrmarapps.helloinnocv.detailactivity;

import android.view.Menu;
import android.view.MenuItem;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.apiclient.apicalls.InnocvApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Mario on 22/09/2017.
 */
@RunWith(PowerMockRunner.class)

public class DetailActivityPresenterTest {
    @Mock
     DetailActivityPresenter presenter;

    @Mock
     DetailActivityView view;

    @Mock
     DetailActivity activity;

    @Mock
    InnocvApi innocvApi;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new  DetailActivityPresenter(view, activity,innocvApi);
    }
    @Test
    public void onOptionsDeleteItemSelected(){
        MenuItem menuItem = mock(MenuItem.class);
        doReturn(R.id.delete_item).when(menuItem).getItemId();
        presenter.onOptionsItemSelected(menuItem);
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
    public void onOptionsHomeItemSelected(){
        MenuItem menuItem = mock(MenuItem.class);
        doReturn(android.R.id.home).when(menuItem).getItemId();
        presenter.onOptionsItemSelected(menuItem);
        verify(activity).finish();
    }

}