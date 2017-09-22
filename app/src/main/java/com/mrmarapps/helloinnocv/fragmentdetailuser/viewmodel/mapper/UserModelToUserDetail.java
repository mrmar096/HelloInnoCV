package com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.mapper;

import com.mrmarapps.helloinnocv.InnoCvUtils;
import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.data.Mapper;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;
import com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel.UserItem;

public class UserModelToUserDetail extends Mapper<UserModel, UserDetail> {


    @Override
    public UserDetail map(UserModel value) {

        return new UserDetail(value.getId(),value.getName(),mapDate(value.getBirthDate()));

    }
    private String mapDate(String birthDate) {
        return InnoCvUtils.formatApiDateToLocal(birthDate);
    }

    @Override
    public UserModel reverseMap(UserDetail value) {
        return null;
    }
}