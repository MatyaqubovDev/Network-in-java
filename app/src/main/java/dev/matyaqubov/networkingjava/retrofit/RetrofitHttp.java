package dev.matyaqubov.networkingjava.retrofit;

import dev.matyaqubov.networkingjava.retrofit.service.PosterService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {
    public  static boolean IS_TESTER=true;
    public static String SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/";
    public static String SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/";


    public static Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static String server() {
        if (IS_TESTER) return SERVER_DEVELOPMENT;
        return SERVER_PRODUCTION;
    }

    public static PosterService posterService=retrofit.create(PosterService.class);
    //....
}
