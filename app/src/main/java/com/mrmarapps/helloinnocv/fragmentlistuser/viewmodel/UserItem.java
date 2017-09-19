package com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.mrmarapps.helloinnocv.R;

import java.util.Date;
import java.util.List;

/**
 * Created by mario on 14/09/17.
 */

public class UserItem extends AbstractItem<UserItem,UserItem.ViewHolder>{
    public int id;
    public String name;
    public String birthDate;


    public UserItem(int id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_sampleitem_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.user_list_item;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);

        viewHolder.name.setText(name);

        viewHolder.id.setText(String.valueOf(id));

        viewHolder.birthDate.setText(String.valueOf(birthDate));

    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText(null);
        holder.id.setText(null);
        holder.birthDate.setText(null);
    }

    //Init the viewHolder for this Item
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    //The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView birthDate;
        protected TextView name;
        protected TextView id;

        public ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.name_user);
            this.birthDate = view.findViewById(R.id.birth_date_user);
            this.id = view.findViewById(R.id.id_user);
        }
    }
}
