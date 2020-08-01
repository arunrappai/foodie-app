package com.code.zomato.retrofit;

import com.code.zomato.SearchInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.code.zomato.config.Constants.API_KEY;

public interface RequestInterface {

  @Headers({"user-key:"+API_KEY})
  @GET("search")
  Call<ResponseBody> getSearch(@Query("lat") double lat, @Query("lon") double lon, @Query("count") int count, @Query("q") String q);
  @Headers({"user-key:"+API_KEY})
  @GET("categories")
  Call<ResponseBody> getCategories();


}
