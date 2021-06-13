package com.timmy.github_silkrode.api

import com.timmy.github_silkrode.db.ReceivedEvent
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("user")
    suspend fun fetchUserOwner(@Header("Authorization") authorization: String): Response<UserInfo>

    @GET("users/{username}/received_events?")
    suspend fun queryReceivedEvents(@Path("username") username: String,
                                    @Query("page") pageIndex: Int,
                                    @Query("per_page") perPage: Int): List<ReceivedEvent>

}
