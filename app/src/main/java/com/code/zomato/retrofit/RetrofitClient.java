package com.code.zomato.retrofit;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.code.zomato.config.Constants.wcfUrl;


public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Context ctx;


    public RetrofitClient(Context ctx) {
        this.ctx = ctx;
        // this default constructor is private and you can't call it like :
        // RetrofitClient client = new RetrofitClient();
        // only way calling it : Retrofit client = RetrofitClient.getInstance();
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            // my token authenticator , I will add this class below to show the logic
  /*          TokenAuthenticator tokenAuthenticator = new TokenAuthenticator();*/

            // I am also using interceptor which controls token if expired
            // lets look at this scenerio : if my token needs to refresh after 10 hours but I came
            // to application after 50 hours and tried to make request.
            // ofc my token is invalid and if I make request it will return 401
            // so this interceptor checks time and refresh token immediately before making request and after makes current request
            // with refreshed token. So I do not get 401 response. But if this fails and I get 401 then my TokenAuthenticator do his job.



            // this is the critical point that helped me a lot.
            // we using only one retrofit instance in our application
            // and it uses this dispatcher which can only do 1 request at the same time

            // the docs says : Set the maximum number of requests to execute concurrently.
            // Above this requests queue in memory, waiting for the running calls to complete.

            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);

            // we using this OkHttp, you can add authenticator, interceptors, dispatchers,
            // logging stuff etc. easily for all your requests just editing this OkHttp
            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(50, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)

                    .dispatcher(dispatcher)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(wcfUrl)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .client(okClient)
                    .build();
        }


        return retrofit;
    }


    /*To make the Call

    Retrofit client= RetrofitClient.getInstance();
    //interface for requests
    RequestInterface request = client.create(RequestInterface.class);
// then do your requests .....

*/

}
