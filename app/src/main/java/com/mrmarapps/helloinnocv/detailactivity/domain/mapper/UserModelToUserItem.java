package com.mrmarapps.helloinnocv.detailactivity.domain.mapper;

import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.data.Mapper;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;

public class UserModelToUserItem extends Mapper<UserModel, UserItem> {


    @Override
    public UserItem map(UserModel value) {

        return new UserItem(value.getId(),value.getName(),value.getBirthDate());

    }

    @Override
    public UserModel reverseMap(UserItem value) {
        return null;
    }
}