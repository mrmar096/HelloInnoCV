package com.mrmarapps.helloinnocv.detailactivity.domain.mapper;

import com.mrmarapps.helloinnocv.InnoCvUtils;
import com.mrmarapps.helloinnocv.apiclient.request.UserRequest.UserRequest;
import com.mrmarapps.helloinnocv.data.Mapper;
import com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel.UserDetail;

public class UserDetailToUserRequest extends Mapper<UserDetail, UserRequest> {


    @Override
    public UserRequest map(UserDetail value) {

        return new UserRequest(value.getId(),value.getName(),mapDate(value.getBirthDate()));

    }

    private String mapDate(String birthDate) {
        return InnoCvUtils.formatLocalDateToApi(birthDate);
    }

    @Override
    public UserDetail reverseMap(UserRequest value) {
        return null;
    }
}