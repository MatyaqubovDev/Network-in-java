package dev.matyaqubov.networkingjava.retrofit;

import com.google.gson.annotations.SerializedName;

public class PosterResp{
    @SerializedName("id")
    int id;

    @SerializedName("userId")
    int userId;

    @SerializedName("title")
    String title;

    @SerializedName("body")
    String body;
}
