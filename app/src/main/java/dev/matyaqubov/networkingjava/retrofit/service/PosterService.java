package dev.matyaqubov.networkingjava.retrofit.service;


import java.util.ArrayList;

import dev.matyaqubov.networkingjava.Poster;
import dev.matyaqubov.networkingjava.retrofit.PosterResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PosterService {
    @Headers("Content-type:application/json")
    @GET("posts")
    Call<ArrayList<PosterResp>> getAllPosts();

    @GET("posts/{id}")
    Call<PosterResp> getSinglePost(@Path("id") int id);

    @POST("posts")
    Call<PosterResp> createPost(@Body Poster poster);

    @PUT("posts{id}")
    Call<PosterResp> updatePost(@Path("id") int id, @Body Poster poster);

    @DELETE("posts{id}")
    Call<PosterResp> deletePost(@Path("id") int id);
}
