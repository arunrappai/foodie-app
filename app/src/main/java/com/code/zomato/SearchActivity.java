package com.code.zomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code.zomato.retrofit.RequestInterface;
import com.code.zomato.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.code.zomato.config.Constants.API_KEY;
import static com.code.zomato.config.Constants.NO_DATA;
import static com.code.zomato.config.Constants.NO_RESPONSE;
import static com.code.zomato.config.Constants.SERVER_PROBLEM;
import static com.code.zomato.config.Constants.SERVICE_FAILED;

public class SearchActivity extends AppCompatActivity {

    private String TAG = SearchActivity.class.getSimpleName();
    private Context context = SearchActivity.this;

    private double latitude=0.0,longitude=0.0;

    private Toolbar toolbar;
    private LinearLayout llSearch;
    private EditText editText;
    private Button btnSearch;
    private LayoutInflater layoutInflater = null;

    private ImageView imgHotel;
    private TextView txtHotelName;
    private TextView txtCuisine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initToolbar();
        llSearch = findViewById(R.id.ll_search_list);
        editText = findViewById(R.id.editText);
        btnSearch = findViewById(R.id.button);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Intent intent;
        intent = getIntent();
        latitude = intent.getDoubleExtra("lat",0.0);
        longitude = intent.getDoubleExtra("long",0.0);

        getSearchList();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSearchList();
            }
        });
    }

    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
    }

    private void getSearchList() {

        Retrofit retrofit = RetrofitClient.getInstance();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<ResponseBody> call = request.getSearch(latitude,longitude, 20,""+editText.getText().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response != null) {

                    Log.e(TAG, "getSearchList URL: " + response.raw().request().url());

                    try{

                        String data = "" + response.body().string();
                        if (data != null) {

                            JSONObject jsonObject = new JSONObject(data);

                            if (jsonObject != null) {

                                JSONArray jsonArray = jsonObject.getJSONArray("restaurants");

                                ArrayList<ResultInfo> searchList = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject c = jsonArray.getJSONObject(i);

                                    JSONObject cat = c.getJSONObject("restaurant");

                                    String id = cat.getString("id");
                                    String name = cat.getString("name");
                                    String thumb = cat.getString("thumb");
                                    String cuisines = cat.getString("cuisines");

                                    searchList.add(new ResultInfo(name,thumb,cuisines));
                                }

                                Log.e(TAG,"searchList.size: "+searchList.size());

                                if(searchList!=null){

                                    llSearch.removeAllViews();

                                    if (searchList.size() > 0) {

                                        for (int ii = 0; ii < searchList.size(); ii++) {

                                            LinearLayout v = (LinearLayout) layoutInflater.inflate(R.layout.row_search, null);

                                            imgHotel = v.findViewById(R.id.img_hotel);
                                            txtHotelName = v.findViewById(R.id.txt_hotel_name);
                                            txtCuisine = v.findViewById(R.id.txt_cuisine);

                                            if(searchList.get(ii).getThumb()!=null){

                                                if(!searchList.get(ii).getThumb().equals("")){

                                                    Picasso.get().load(""+searchList.get(ii).getThumb())
                                                            //.error(R.drawable.no_image)
                                                            .placeholder(R.drawable.progress_animation)
                                                            .into(imgHotel);
                                                }else {

                                                    imgHotel.setBackgroundResource(R.drawable.no_image);
                                                }
                                            }else {

                                                imgHotel.setBackgroundResource(R.drawable.no_image);
                                            }


                                            txtHotelName.setText(""+searchList.get(ii).getName());
                                            txtCuisine.setText(""+searchList.get(ii).getCuisines());
                                            //Log.e(TAG,"name: "+searchList.get(ii).getName());

                                            llSearch.addView(v);
                                        }
                                    }
                                }
                            }
                        }


                    }catch (Exception e){

                        Log.e(TAG,"exception: "+e.getMessage());
                    }

                } else {

                    Toast.makeText(context, SERVER_PROBLEM, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                if (t != null) {

                    if (t.getMessage() != null) {

                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            goBack();
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        //do your intent here
        super.onBackPressed();
        goBack();
    }

    private void goBack(){

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }
}