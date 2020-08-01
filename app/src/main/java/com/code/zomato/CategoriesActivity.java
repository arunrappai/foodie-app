package com.code.zomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.code.zomato.retrofit.RequestInterface;
import com.code.zomato.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.code.zomato.config.Constants.API_KEY;
import static com.code.zomato.config.Constants.SERVER_PROBLEM;

public class CategoriesActivity extends AppCompatActivity {

    private String TAG = CategoriesActivity.class.getSimpleName();
    private Context context = CategoriesActivity.this;

    private Toolbar toolbar;
    private LinearLayout llCategory;
    private LayoutInflater layoutInflater = null;

    private TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initToolbar();
        llCategory = findViewById(R.id.ll_category_list);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        getCategories();
    }

    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
    }

    private void getCategories() {

        Retrofit retrofit = RetrofitClient.getInstance();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<ResponseBody> call = request.getCategories();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response != null) {

                    Log.e(TAG, "getCategories URL: " + response.raw().request().url());

                    try{

                        String data = "" + response.body().string();

                        if (data != null) {

                            JSONObject jsonObject = new JSONObject(data);

                            if (jsonObject != null) {

                                JSONArray jsonArray = jsonObject.getJSONArray("categories");

                                ArrayList<Category> categoryList = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject c = jsonArray.getJSONObject(i);

                                    JSONObject cat = c.getJSONObject("categories");

                                    String id = cat.getString("id");
                                    String name = cat.getString("name");

                                    categoryList.add(new Category(id, name));
                                }

                                Log.e(TAG,"categoryList.size: "+categoryList.size());

                                if(categoryList!=null){

                                    llCategory.removeAllViews();

                                    if (categoryList.size() > 0) {

                                        for (int ii = 0; ii < categoryList.size(); ii++) {

                                            LinearLayout v = (LinearLayout) layoutInflater.inflate(R.layout.row_category, null);

                                            txtCategory = v.findViewById(R.id.txt_category);

                                            txtCategory.setText("" + categoryList.get(ii).getName());

                                            llCategory.addView(v);
                                        }
                                    }
                                }

                            }
                        }
                    }catch (Exception e){

                        Log.e(TAG, "Exception: " + e.toString());
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