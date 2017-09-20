package com.mrmarapps.helloinnocv.fragmentlistuser;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.listeners.ItemFilterListener;
import com.mikepenz.fastadapter_extensions.drag.ItemTouchCallback;
import com.mikepenz.materialize.MaterializeBuilder;
import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.adapters.InnoCvAdapter;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentListUserView extends BaseMVPFragmentView<FragmentListUser,FragmentListUserView.Actions>  implements ItemTouchCallback, ItemFilterListener<UserItem> {

    @BindView(R.id.recycler_users)
    public RecyclerView recyclerUsers;

    @BindView(R.id.swipe_refresh_user_list)
    public SwipeRefreshLayout swipeRefreshLayout;

    private InnoCvAdapter<UserItem> innoCvAdapter;
    private LinearLayoutManager mLayoutManager;

    @Inject
    public FragmentListUserView(FragmentListUser fragment) {
        super(fragment);
    }

    @Override
    public void initView() {

        setupSwipeRefresh();
        setupFasItemAdapter();

        ArrayList<UserItem> userItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            userItems.add(new UserItem(10+i,"Hola User ==> 0"+i,format.format(new Date())));
        }
        setList(userItems);


    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                actions.onRefreshData();

            }
        });

    }


    private void setupFasItemAdapter() {
        new MaterializeBuilder().withActivity(fragment.getActivity()).build();

        innoCvAdapter = new InnoCvAdapter<>();

        innoCvAdapter.withOnClickListener(new FastAdapter.OnClickListener<UserItem>() {
            @Override
            public boolean onClick(View v, IAdapter<UserItem> adapter, UserItem item, int position) {
                innoCvAdapter.selectItem(v,position);
                actions.onUserClicked(item);
                return true;
            }
        });

        configureFilter();

        if(recyclerUsers==null){
            recyclerUsers = (RecyclerView) findViewById(R.id.recycler_users);
        }
        mLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerUsers.setLayoutManager(mLayoutManager);
        recyclerUsers.setHasFixedSize(true);
        recyclerUsers.swapAdapter(innoCvAdapter,true);
    }


    public void stopRefresh(){
        if(swipeRefreshLayout!=null && swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void startRefresh(){
        if(swipeRefreshLayout!=null && !swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void filterList(String query){
        innoCvAdapter.filter(query);
    }

    private void configureFilter() {
        innoCvAdapter.getItemFilter().withFilterPredicate(new IItemAdapter.Predicate<UserItem>() {
            @Override
            public boolean filter(UserItem item, CharSequence constraint) {
                boolean itemName = String.valueOf(item.getName()).contains(String.valueOf(constraint));
                return  !itemName;
            }
        });
    }


    public void setList(List<UserItem> users){
        getAdapter().add(users);

    }

    private InnoCvAdapter<UserItem> getAdapter() {
        if(innoCvAdapter ==null){
            setupFasItemAdapter();
        }
        return innoCvAdapter;
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

    public void deleteItemSelected() {
        int positionSelected = getAdapter().getPositionSelected();
        getAdapter().remove(positionSelected);
    }

    public interface Actions extends ViewActions{
        void onRefreshData();

        void onUserClicked(UserItem item);
    }
}
