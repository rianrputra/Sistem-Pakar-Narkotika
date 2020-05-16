package com.rianprojek.sistempakarnarkotika.Api;

import com.rianprojek.sistempakarnarkotika.Model.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rian on 10/09/2018.
 */

public interface ApiRequestPengguna {
    @FormUrlEncoded
    @POST("Kerja.php?btn_client=insert_client")
    Call<ResponseUser> insert(@Field("username") String username,
                              @Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("Kerja.php?btn_client=login")
    Call<ResponseUser> login(@Field("username") String username,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("Kerja.php?btn_client=login2")
    Call<ResponseUser> login2(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("Kerja.php?btn_client=select_client")
    Call<ResponseUser> select_client(@Field("username") String username);
}
