package com.mrmarapps.helloinnocv.mainactivity.viewmodel;

import com.mrmarapps.helloinnocv.data.Mapper;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;

public class UserItemToUserDetail extends Mapper<UserItem, UserDetail> {


    @Override
    public UserDetail map(UserItem value) {
        return new UserDetail(value.getId(),value.getName(),value.getBirthDate());
    }

    @Override
    public UserItem reverseMap(UserDetail value) {
        return new UserItem(value.getId(),value.getName(),value.getBirthDate());
    }
}