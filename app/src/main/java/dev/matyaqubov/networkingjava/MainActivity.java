package dev.matyaqubov.networkingjava;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import dev.matyaqubov.networkingjava.retrofit.PosterResp;
import dev.matyaqubov.networkingjava.retrofit.RetrofitHttp;
import dev.matyaqubov.networkingjava.volley.VolleyHandler;
import dev.matyaqubov.networkingjava.volley.VolleyHttp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    RecyclerView recyclerView;
    ProgressBar progress;
    FloatingActionButton b_floating;
    ArrayList<Poster> posters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        posters = new ArrayList();
        recyclerView = findViewById(R.id.recyclerView);
        b_floating = findViewById(R.id.b_floating);
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        apiPosterList();

    }

    private void apiPosterList() {
        progress.setVisibility(View.VISIBLE);
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Poster[] postArray = new Gson().fromJson(response, Poster[].class);
                posters.clear();
                posters.addAll(Arrays.asList(postArray));
                progress.setVisibility(View.GONE);
                refreshAdapter(posters);
                Log.d("@@@", "onSuccess: ");
            }

            @Override
            public void onError(String error) {
                Logger.e("@@@", error);
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void refreshAdapter(ArrayList<Poster> posters) {
        recyclerView.setAdapter(new PosterAdapter(this, posters));
    }

    public void dialogPoster(Poster post) {
        new AlertDialog.Builder(this).setTitle("Delete Post")
                .setMessage("Are you sure to delete this post")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        apiPosterDelete(post);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void apiPosterDelete(Poster post) {
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + post.id, new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Logger.d("@@@", response);
                apiPosterList();
            }

            @Override
            public void onError(String error) {

            }
        });
    }


    //Volley All Methods

    public void workWithVolley(Poster poster) {
        getPostersWithVolley();
        postPosterWithVolley(poster);
        putPosterWithVolley(poster);
        deletePosterWithVolley(poster);
    }

    private void deletePosterWithVolley(Poster poster) {
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "onError: " + error);
            }
        });
    }

    private void putPosterWithVolley(Poster poster) {
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "onError: " + error);
            }
        });
    }

    public void postPosterWithVolley(Poster poster) {
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "onError: " + error);
            }
        });
    }

    public void getPostersWithVolley() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "onError: " + error);
            }
        });
    }


    //Retrofit All Methods
    public void workWithRetrofit(Poster poster) {
        getPostersWithRetrofit();
        getSinglePosterWithRetrofit(poster);
        createPosterWithRetrofit(poster);
        updatePosterWithRetrofit(poster);
        deletePosterWithRetrofit(poster);
    }

    private void deletePosterWithRetrofit(Poster poster) {
        RetrofitHttp.posterService.deletePost(poster.id).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {

            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {

            }
        });
    }

    private void updatePosterWithRetrofit(Poster poster) {
        RetrofitHttp.posterService.updatePost(poster.id,poster).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {

            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {

            }
        });
    }

    private void createPosterWithRetrofit(Poster poster) {
        RetrofitHttp.posterService.createPost(poster).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {
                //
            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {

            }
        });
    }

    private void getSinglePosterWithRetrofit(Poster poster) {
        RetrofitHttp.posterService.getSinglePost(poster.id).enqueue(new Callback<PosterResp>() {
            @Override
            public void onResponse(Call<PosterResp> call, Response<PosterResp> response) {
                //some works
            }

            @Override
            public void onFailure(Call<PosterResp> call, Throwable t) {
                //some works
            }
        });
    }

    private void getPostersWithRetrofit() {
        RetrofitHttp.posterService.getAllPosts().enqueue(new Callback<ArrayList<PosterResp>>() {
            @Override
            public void onResponse(Call<ArrayList<PosterResp>> call, Response<ArrayList<PosterResp>> response) {
                Log.d(TAG, "onResponse: " + response);
            }

            @Override
            public void onFailure(Call<ArrayList<PosterResp>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}