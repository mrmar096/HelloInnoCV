package com.mrmarapps.helloinnocv.apiclient.apicalls;

import com.mrmarapps.helloinnocv.apiclient.model.UserModel.UserModel;
import com.mrmarapps.helloinnocv.apiclient.request.UserRequest.UserRequest;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mario on 21/09/17.
 */

public interface InnocvApi {

    @GET("user/getall")
    Observable<List<UserModel>> getUsers();

    @GET("user/get/{id}")
    Observable<UserModel> getUser(@Path("id") String id);

    @POST("user/create")
    Observable<UserModel> postUser(
            @Body
                    UserRequest requestModel
    );

    @POST("user/update")
    Observable<UserModel> updateUser(
            @Body
                    UserRequest requestModel
    );

    @GET("user/remove/{id}")
    Observable<BaseModel> removeUser(@Path("id") String id);


}
