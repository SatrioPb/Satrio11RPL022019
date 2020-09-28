package com.example.satrio11rpl022019;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListDataFavourite extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapterFavourite adapter;
    private ArrayList<ModelMovieRealm> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);

    }


}