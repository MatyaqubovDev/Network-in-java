package dev.matyaqubov.networkingjava.volley;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.matyaqubov.networkingjava.Logger;
import dev.matyaqubov.networkingjava.MyApplication;
import dev.matyaqubov.networkingjava.Poster;

public class VolleyHttp {

    public static String TAG = VolleyHttp.class.getSimpleName();
    public static boolean IS_TESTER = true; //true bu test server false bu production server
    public static String SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/";
    public static String SERVER_PRODUCTION = "https://62219fc9afd560ea69b5292a.mockapi.io/";


    public static String server(String url) {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT + url;
        return SERVER_PRODUCTION + url;
    }

    public static HashMap<String, String> headers() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-type", "application/json; charset=UTF-8");
        return headers;
    }

    /**
     * Request Method`s
     */

    public static void get(String api, HashMap<String, String> params, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server(api), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.d(TAG, response);
                volleyHandler.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                volleyHandler.onError(error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void post(String api, HashMap<String, String> body, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server(api), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Logger.d(TAG, response);
                volleyHandler.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                volleyHandler.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(body).toString().getBytes();
            }
        };
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void put(String api, HashMap<String, String> body, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, server(api), response -> {
            Logger.d(TAG, response);
            volleyHandler.onSuccess(response);
        }, error -> {
            Logger.e(TAG, error.toString());
            volleyHandler.onError(error.toString());
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(body).toString().getBytes();
            }
        };
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    public static void del(String api, VolleyHandler volleyHandler) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, server(api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(TAG, response);
                        volleyHandler.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(TAG, error.toString());
                volleyHandler.onError(error.toString());
            }
        });
        MyApplication.instance.addToRequestQueue(stringRequest);
    }

    /**
     * Request Api`s
     */

    public static String API_LIST_POST = "posts";
    public static String API_SINGLE_POST = "posts/"; //id
    public static String API_CREATE_POST = "posts";
    public static String API_UPDATE_POST = "posts/"; //id
    public static String API_DELETE_POST = "posts/"; //id

    /**
     *  Request Param`s
     */

    public static HashMap<String, String> paramsEmpty() {
        return new HashMap<String, String>();
    }

    public static HashMap<String, String> paramsCreate(Poster poster ) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("title", poster.getTitle());
        params.put("body", poster.getBody());
        params.put("userId", String.valueOf(poster.getUserId()));
        return params;
    }

    public static HashMap<String, String> paramsUpdate(Poster poster){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(poster.getId()));
        params.put("title", poster.getTitle());
        params.put("body", poster.getBody());
        params.put("userId", String.valueOf(poster.getId()));
        return params;
    }

    /**
     *  Response Parse`s
     */

}
