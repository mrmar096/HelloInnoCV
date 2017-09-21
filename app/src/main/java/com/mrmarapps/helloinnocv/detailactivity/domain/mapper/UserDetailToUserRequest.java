package com.mrmarapps.helloinnocv.detailactivity.domain.mapper;

import com.mrmarapps.helloinnocv.apiclient.request.UserRequest.UserRequest;
import com.mrmarapps.helloinnocv.data.Mapper;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;

public class UserDetailToUserRequest extends Mapper<UserDetail, UserRequest> {


    @Override
    public UserRequest map(UserDetail value) {

        return new UserRequest(value.getId(),value.getName(),value.getBirthDate());

    }

    @Override
    public UserDetail reverseMap(UserRequest value) {
        return null;
    }
}