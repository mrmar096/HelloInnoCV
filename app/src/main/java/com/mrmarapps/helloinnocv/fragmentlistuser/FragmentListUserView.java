package com.mrmarapps.helloinnocv.fragmentlistuser;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mrmarapps.helloinnocv.R;
import com.mrmarapps.helloinnocv.mvp.ViewActions;
import com.mrmarapps.helloinnocv.mvp.fragment.BaseMVPFragmentView;

import butterknife.BindView;

/**
 * Created by mario on 14/09/17.
 */

public class FragmentListUserView extends BaseMVPFragmentView<FragmentListUser,FragmentListUserView.Actions>{

    @BindView(R.id.recycler_users)
    public RecyclerView recyclerUsers;

    public FragmentListUserView(FragmentListUser fragment) {
        super(fragment);
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(fragment.getContext(), 2);
        recyclerUsers.setLayoutManager(linearLayoutManager);
    }

   /* private AttachmentsListItemGalleryAdapter getAdapter() {
        AttachmentsListItemGalleryAdapter adapter = (AttachmentsListItemGalleryAdapter) recyclerview.getAdapter();
        if(adapter == null){
            adapter = new AttachmentsListItemGalleryAdapter(this);
            final boolean refreshView = true;
            recyclerview.swapAdapter(adapter,refreshView);
        }
        return adapter;
    }

    public void setList(List<User> users){
        getAdapter().setItems(users);
    }

    public void addUser(User user){
        getAdapter().addItem(user);
    }
    public void addUser(User user, int pos) {
        getAdapter().addItem(pos, user);
    }

    public void removeUser(int position){
        getAdapter().removeItem(position);
    }
    public void removeUser(User user){
        getAdapter().removeItem(position);
    }
*/

    public class Actions implements ViewActions{
    }
}
