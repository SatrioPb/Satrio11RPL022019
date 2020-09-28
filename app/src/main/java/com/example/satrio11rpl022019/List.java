package com.example.satrio11rpl022019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList;
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        //addData();
        addDataOnline();
    }

    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setOriginal_title("Judul Film");
        data1.setPoster_path("https://image.tmdb.org/t/p/w500/k68nPLbIST6NP96JmTxmZijEvCA.jpg");
        data1.setAdult(false);
        data1.setOverview("Deskripsi Film disini");
        data1.setVote_count(100);
        data1.setRelease_date("01-01-2020");
        DataArrayList.add(data1);


        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




    }
    void addDataOnline(){
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=93a67c23268a155084de6d56fe489d9d")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hasiljson","onResponse: "+response.toString());
                        DataArrayList  = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku=new Model();
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setPoster_path("https://image.tmdb.org/t/p/w500"+jsonObject.getString("poster_path"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setVote_count(jsonObject.getInt("vote_count"));
                                DataArrayList.add(modelku);
                            }

                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model Movie = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(),DataMovie.class);
                                    intent.putExtra("judul",Movie.original_title);
                                    intent.putExtra("path",Movie.poster_path);
                                    intent.putExtra("date",Movie.release_date);
                                    intent.putExtra("deskripsi",Movie.overview);
                                    startActivity(intent);
                                    Toast.makeText(List.this,"Deskripsi Film" + position, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } catch ( JSONException e) {
                            e.printStackTrace();
                        }
                    }



                    @Override
                    public void onError(ANError error) {
                        Log.d("myerror","onError errorcode: "+ error.getErrorCode());
                        Log.d("myerror","onError errorcode: " +error.getErrorBody());
                        Log.d("myerror","onError errorcode: "+ error.getErrorDetail());

                    }
                });



    }
}