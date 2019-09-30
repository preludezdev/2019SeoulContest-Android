package com.seoulcontest.firstcitizen.network

import com.seoulcontest.firstcitizen.data.vo.*
import com.seoulcontest.firstcitizen.network.vo.GetTokenRequest
import com.seoulcontest.firstcitizen.network.vo.GetTokenResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface FirstCitizenApiService {

    @POST("account/get_token/")
    fun getToken(@Body userInfo: GetTokenRequest): Call<GetTokenResponse>

    @POST("account/")
    fun createAccount(@Body pararm: Account): Call<User>

    @GET("account/{id}")
    fun getUser(@Path("id") id: String): Call<User>

    @GET("request/boundary/")
    fun getRequestsByPositionWithToken(
        @Header("Authorization") token: String,
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float
    ): Call<List<BriefRequest>>

    @GET("request/boundary/")
    fun getRequestsByPositionWithNoToken(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float
    ): Call<List<BriefRequest>>

    @GET("request/category/")
    fun getCategoryList(): Call<List<Category>>

    @GET("request/{id}/")
    fun getDetailRequestById(@Path("id") id: Int): Call<Request>

    @Multipart
    @POST("request/")
    fun createRequest(
        @Header("Authorization") token: String,
        @PartMap partMap: HashMap<String, RequestBody>,
//        @Part("category") type: RequestBody, @Part("title") title: RequestBody,
//        @Part("content") content: RequestBody, @Part("latitude") latitude: RequestBody,
//        @Part("longitude") longitude: RequestBody,
        @Part images: List<MultipartBody.Part>
    ): Call<Request>
}