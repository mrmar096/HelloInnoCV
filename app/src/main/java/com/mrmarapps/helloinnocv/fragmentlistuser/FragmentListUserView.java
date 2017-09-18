package com.mrmarapps.helloinnocv.fragmentlistuser;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.ISelectionListener;
import com.mikepenz.fastadapter.adapters.HeaderAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.ItemFilterListener;
import com.mikepenz.fastadapter_extensions.ActionModeHelper;
import com.mikepenz.fastadapter_extensions.UndoHelper;
import com.mikepenz.fastadapter_extensions.drag.ItemTouchCallback;
import com.mikepenz.materialize.MaterializeBuilder;
import com.mikepenz.materialize.util.UIUtils;
import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.User;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentListUserView extends BaseMVPFragmentView<FragmentListUser,FragmentListUserView.Actions>  implements ItemTouchCallback, ItemFilterListener<UserItem> {

    @BindView(R.id.recycler_users)
    public RecyclerView recyclerUsers;
    private FastItemAdapter<UserItem> mFastAdapter;

    @Inject
    public FragmentListUserView(FragmentListUser fragment) {
        super(fragment);
    }

    @Override
    public void initView() {

        new MaterializeBuilder().withActivity(fragment.getActivity()).build();

        initFasItemAdapter();

        ArrayList<UserItem> userItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userItems.add(new UserItem(i,"Hola User ==> "+i,new Date()));
        }
        setList(userItems);
    }

    private void initFasItemAdapter() {
        mFastAdapter = new FastItemAdapter<>();
        mFastAdapter.withOnClickListener(new FastAdapter.OnClickListener<UserItem>() {
            @Override
            public boolean onClick(View v, IAdapter<UserItem> adapter, UserItem item, int position) {
                showMessage(String.valueOf(item.getId()));
                return false;
            }
        });

        configureFilter();

        if(recyclerUsers==null){
            recyclerUsers = (RecyclerView) findViewById(R.id.recycler_users);
        }

        recyclerUsers.swapAdapter(mFastAdapter,true);
    }



    public void filterList(String query){
        mFastAdapter.filter(query);
    }

    private void configureFilter() {
        mFastAdapter.getItemFilter().withFilterPredicate(new IItemAdapter.Predicate<UserItem>() {
            @Override
            public boolean filter(UserItem item, CharSequence constraint) {
                return !String.valueOf(item.getId()).equals(String.valueOf(constraint));
            }
        });
    }


    public void setList(List<UserItem> users){
        getAdapter().add(users);

    }

    private FastItemAdapter<UserItem> getAdapter() {
        if(mFastAdapter==null){
            initFasItemAdapter();
        }
        return mFastAdapter;
    }

    public void addUser(UserItem user){
        getAdapter().add(user);

    }
    public void addUser(UserItem user, int pos) {
        getAdapter().add(pos,user);
    }

    public void removeUser(int position){
        getAdapter().remove(position);
    }

    @Override
    public void itemsFiltered(@Nullable CharSequence constraint, @Nullable List<UserItem> results) {

    }

    @Override
    public void onReset() {

    }

    @Override
    public boolean itemTouchOnMove(int oldPosition, int newPosition) {
        return false;
    }

    @Override
    public void itemTouchDropped(int oldPosition, int newPosition) {

    }

    public interface Actions extends ViewActions{
    }
}
